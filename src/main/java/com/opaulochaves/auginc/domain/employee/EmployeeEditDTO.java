package com.opaulochaves.auginc.domain.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author paulo
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmployeeEditDTO {
    
    private Long id;

    @JsonProperty("first_name")
    @NotEmpty
    @Size(max = 255)
    private String firstName;

    @JsonProperty("last_name")
    @NotEmpty
    @Size(max = 255)
    private String lastName;

    @NotEmpty
    @Email
    @Size(max = 255)
    private String email;
    
    @Length(min = 8, max = 20)
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Deve ter apenas letras e números")
    private String password;
    
    @JsonProperty("phone")
    @Size(max = 20)
    private String phoneNumber;
    
    private String title;
    
    private boolean enabled = true;

}
