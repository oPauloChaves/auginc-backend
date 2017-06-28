package com.opaulochaves.auginc.core.security;

import com.opaulochaves.auginc.domain.employee.Employee;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class EmployeeDetails implements UserDetails {
    
    private static final long serialVersionUID = 1L;

    private final Employee employee;
    private final Collection<? extends GrantedAuthority> authorities;

    public EmployeeDetails(Employee employee) {
        this.employee = employee;
        this.authorities = employee.getAuthorities();
    }

    public EmployeeDetails(Employee employee, Collection<? extends GrantedAuthority> authorities) {
        this.employee = employee;
        this.authorities = authorities;
    }

    public Employee getEmployee() {
        return employee;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return employee.isEnabled();
    }
    
}
