package com.opaulochaves.auginc.core.security;

import com.opaulochaves.auginc.domain.employee.Employee;
import com.opaulochaves.auginc.domain.employee.EmployeeRepository;
import static java.util.Arrays.asList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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
public class InMemorySecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private EmployeeRepository employeeRepository;

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
        http.
                authorizeRequests().anyRequest().fullyAuthenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .httpBasic()
                .realmName("trackr development realm")
            .and()
                .csrf().disable();
    }
}