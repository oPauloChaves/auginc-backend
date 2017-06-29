package com.opaulochaves.auginc.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Bean with utility methods to check custom business logic to handle security
 *
 * @author paulo
 */
@Component
public class WebSecurity {

    /**
     * Checks if the user id from the URL matches the user id of the logged user
     *
     * @param authentication
     * @param id
     * @return
     */
    public boolean checkUserID(Authentication authentication, Long id) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeDetails employeeDetails = (EmployeeDetails) userDetails;

        return employeeDetails.getEmployee().getId().equals(id);
    }

}
