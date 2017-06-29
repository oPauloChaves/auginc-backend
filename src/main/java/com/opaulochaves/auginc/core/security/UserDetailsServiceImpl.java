package com.opaulochaves.auginc.core.security;

import com.opaulochaves.auginc.domain.employee.Employee;
import com.opaulochaves.auginc.domain.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final EmployeeService service;

    @Autowired
    public UserDetailsServiceImpl(EmployeeService service) {
        this.service = service;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = service.findByEmail(username);

        if (employee == null) {
            throw new UsernameNotFoundException("No user found with email: " + username);
        }

        return new EmployeeDetails(employee);
    }

}
