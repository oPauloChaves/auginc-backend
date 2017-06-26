package com.opaulochaves.auginc.core.security;

import com.opaulochaves.auginc.core.AugincProperties;
import com.opaulochaves.auginc.domain.employee.Employee;
import com.opaulochaves.auginc.domain.employee.EmployeeRepository;
import static java.util.Arrays.asList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * This configuration enables HTTP Basic authentication and uses the employees as credentials, all with no password and admin access.
 *
 * It is only enabled when the oauth profile is off.
 */
@EnableWebSecurity
@Configuration
@Profile("http-basic")
@EnableConfigurationProperties({AugincProperties.class})
public class InMemorySecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    private AugincProperties properties;

    public InMemorySecurityConfiguration(AugincProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(username -> {
                        Employee employee = employeeRepository.findByEmail(username);
                        if (employee == null) {
                            throw new BadCredentialsException("User not found");
                        }
                        return new User(username, "", asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                    }
            );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .anyRequest().fullyAuthenticated()
            .and()
                .httpBasic()
                .realmName(properties.getRealmName())
            .and()
                .csrf().disable();
        
        http.headers().addHeaderWriter((request, response) -> {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            if (request.getMethod().equals("OPTIONS")) {
                response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
            }
        });
    }
}