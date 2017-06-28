package com.opaulochaves.auginc.web.util;

import com.opaulochaves.auginc.core.security.EmployeeDetails;
import com.opaulochaves.auginc.domain.employee.authorities.Authority;
import com.opaulochaves.auginc.domain.employee.authorities.AuthorityName;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author paulo
 */
@Component
public class JwtTokenUtil {
    
    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_USERID = "userid";
    static final String CLAIM_KEY_AUDIENCE = "audience";
    static final String CLAIM_KEY_CREATED = "created";
    static final String CLAIM_KEY_ROLES = "roles";

    private static final String AUDIENCE_UNKNOWN = "unknown";
    private static final String AUDIENCE_WEB = "web";
    private static final String AUDIENCE_MOBILE = "mobile";
    private static final String AUDIENCE_TABLET = "tablet";

    @Value("${auginc.jwt.secret}")
    private String secret;

    @Value("${auginc.jwt.expiration}")
    private Long expiration;
    
    public String getEmailFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception ignored) {
            username = null;
        }
        return username;
    }

    public Long getUserIdFromToken(String token) {
        Long id;
        try {
            final Claims claims = getClaimsFromToken(token);
            String tempId = (String) claims.get(CLAIM_KEY_USERID);
            id = tempId == null ? null : Long.valueOf(tempId);
        } catch (Exception ignored) {
            id = null;
        }
        return id;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception ignored) {
            expiration = null;
        }
        return expiration;
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = getClaimsFromToken(token);
            audience = (String) claims.get(CLAIM_KEY_AUDIENCE);
        } catch (Exception ignored) {
            audience = null;
        }
        return audience;
    }

    @SuppressWarnings("unchecked")
    public Collection<GrantedAuthority> getAuthoritiesFromToken(String token) {
        final Collection<GrantedAuthority> authorities = new ArrayList<>();
        // "roles": [ "ROLE_USER" ]
        final List<String> roles;
        try {
            final Claims claims = getClaimsFromToken(token);
            roles = (List<String>) claims.get(CLAIM_KEY_ROLES);
            
            if (!roles.isEmpty()) {
                roles.forEach(role -> {
                    role = String.format("%s%s", Authority.ROLE_PREFIX, role);
                    AuthorityName authorityName = AuthorityName.valueOf(role);
                    Authority authority = new Authority(authorityName);
                    authorities.add(authority);
                });
            }
        } catch (Exception ignored) {
        }
        return authorities;
    }
    
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ignored) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private String generateAudience(Device device) {
        String audience = AUDIENCE_UNKNOWN;
        if (device.isNormal()) {
            audience = AUDIENCE_WEB;
        } else if (device.isTablet()) {
            audience = AUDIENCE_TABLET;
        } else if (device.isMobile()) {
            audience = AUDIENCE_MOBILE;
        }
        return audience;
    }

    private Boolean ignoreTokenExpiration(String token) {
        String audience = getAudienceFromToken(token);
        return (AUDIENCE_TABLET.equals(audience) || AUDIENCE_MOBILE.equals(audience));
    }

    public String generateToken(EmployeeDetails employeeDetails, Device device) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, employeeDetails.getUsername());
        claims.put(CLAIM_KEY_USERID, employeeDetails.getEmployee().getId());
        claims.put(CLAIM_KEY_AUDIENCE, generateAudience(device));
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_ROLES, generateAuthorities(employeeDetails));
        return generateToken(claims);
    }

    /**
     * Gera uma lista de roles a ser ser adicionada no token com o seguinte formato<br><br>
     * 
     * <pre>
     * ["USER", "ADMIN"]
     * </pre>
     * 
     * OBS: Ao ler a lista de roles do token, deve ser adiconado o prefixo <i>ROLE_</i>
     * 
     * @param usuarioDetails
     * @return 
     */
    private List<String> generateAuthorities(EmployeeDetails employeeDetails) {
        List<String> list = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = employeeDetails.getAuthorities();
        if (authorities != null) {
            authorities.forEach(authority -> list.add(authority.getAuthority().replace(Authority.ROLE_PREFIX, "")));
        }
        return list;
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception ignored) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        EmployeeDetails user = (EmployeeDetails) userDetails;
        final String username = getEmailFromToken(token);
        final Date created = getCreatedDateFromToken(token);
        //final Date expiration = getExpirationDateFromToken(token);
        return (username.equals(user.getUsername())
                && !isTokenExpired(token)
                && !isCreatedBeforeLastPasswordReset(created, user.getEmployee().getLastResetPasswordDate()));
    }
}
