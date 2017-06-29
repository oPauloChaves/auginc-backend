package com.opaulochaves.auginc.domain.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author paulo
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public final class EmployeeDTO {
    
    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String email;
    
    @JsonProperty("phone")
    private String phoneNumber;
    
    private String title;
    
    private boolean enabled;
    
    @JsonProperty("fullname")
    public String getFullname() {
        return getFirstName() + " " + getLastName();
    }

}
