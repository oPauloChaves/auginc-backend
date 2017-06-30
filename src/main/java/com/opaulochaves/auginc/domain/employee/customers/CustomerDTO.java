package com.opaulochaves.auginc.domain.employee.customers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opaulochaves.auginc.domain.common.Address;
import javax.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Paulo Chaves
 */
@Getter
@Setter
public class CustomerDTO {

    private Long id;

    @NotEmpty
    @JsonProperty("first_name")
    private String firstName;

    @NotEmpty
    @JsonProperty("last_name")
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    @JsonProperty("phone")
    private String phoneNumber;

    @JsonProperty("cellphone")
    private String cellNumber;

    private String note;

    @JsonProperty("employee_id")
    private Long employeeID;

    @Valid
    private Address address;
}
