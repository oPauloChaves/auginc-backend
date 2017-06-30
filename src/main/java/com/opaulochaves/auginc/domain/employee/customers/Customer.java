package com.opaulochaves.auginc.domain.employee.customers;

import com.opaulochaves.auginc.domain.common.AbstractEntity;
import com.opaulochaves.auginc.domain.common.Address;
import com.opaulochaves.auginc.domain.employee.Employee;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * The entity represents an employee's customer. <br><br>
 * 
 * Note: The same e-mail may happen many times but not for the same employee.<br>
 * To enforce that, this class has a unique constraint (email & employee_id)
 *
 * @author paulo
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "employee_id"})})
public class Customer extends AbstractEntity {

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
    
    @Valid
    @Embedded
    private Address address;
    
    @ManyToOne
    private Employee employee;

}
