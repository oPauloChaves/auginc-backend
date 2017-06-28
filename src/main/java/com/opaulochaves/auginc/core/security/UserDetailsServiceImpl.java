package com.opaulochaves.auginc.core.security;

import com.opaulochaves.auginc.domain.employee.Employee;
import com.opaulochaves.auginc.domain.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(username);

        if (employee == null) {
            throw new UsernameNotFoundException("No user found with email: " + username);
        }

        return new EmployeeDetails(employee);
    }

}
