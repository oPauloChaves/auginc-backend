package com.opaulochaves.auginc.domain.employee.customers;

import com.opaulochaves.auginc.domain.common.Address;
import com.opaulochaves.auginc.domain.employee.Employee;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * The entity represents an employee's customer. <br><br>
 * 
 * Note: The same e-mail may happen many times but not for the same employee.<br>
 * To enforce that, this class has a unique constraint (email & employee_id)
 *
 * @author paulo
 */
@Entity
@Getter
@Setter
@Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "employee_id"})})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Column
    @Email
    @NotEmpty
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "cell_number")
    private String cellNumber;
    
    private String note;
    
    private BigDecimal latitude;
    
    private BigDecimal longitude;
    
    @Valid
    @Embedded
    private Address address;
    
    @ManyToOne
    private Employee employee;

}
