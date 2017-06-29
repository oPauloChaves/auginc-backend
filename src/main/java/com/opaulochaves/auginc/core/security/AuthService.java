package com.opaulochaves.auginc.core.security;

import com.opaulochaves.auginc.domain.employee.Employee;
import com.opaulochaves.auginc.domain.employee.EmployeeService;
import com.opaulochaves.auginc.web.dto.LoginRequestDTO;
import com.opaulochaves.auginc.web.util.JwtTokenUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author paulo
 */
@Service
public class AuthService {

    @Value("${auginc.jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeeDetails authenticate(HttpServletRequest request, HttpServletResponse response,
            LoginRequestDTO loginRequest, Device device) throws AuthenticationException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final Employee employee = employeeService.findByEmail(loginRequest.getUsername());
        final EmployeeDetails employeeDetails = new EmployeeDetails(employee);
        return employeeDetails;
    }

    public String refreshToken(HttpServletRequest request) {
        String authToken = request.getHeader(this.tokenHeader);

        // authToken.startsWith("Bearer ")
        // String authToken = header.substring(7);
        String email = jwtTokenUtil.getEmailFromToken(authToken);
        final Employee employee = employeeService.findByEmail(email);
        if (jwtTokenUtil.canTokenBeRefreshed(authToken, employee.getLastResetPasswordDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(authToken);
            return refreshedToken;
        }
        return null;
    }
}
