package com.opaulochaves.auginc.domain.employee;

import com.opaulochaves.auginc.domain.common.AbstractEntity;
import com.opaulochaves.auginc.domain.employee.authorities.Authority;
import com.opaulochaves.auginc.domain.employee.brands.Brand;
import com.opaulochaves.auginc.domain.employee.customers.Customer;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * This entity class contains the information of a single employee entry and the
 * methods that are used to create new employee entries and to modify the
 * information of an existing employee entry.
 *
 * @author Petri Kainulainen
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name = "employees")
public class Employee extends AbstractEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Size(max = 70, min = 6)
    @Column(nullable = false)
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String title;

    private boolean enabled = true;

    private boolean deleted = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_authority",
            joinColumns = {
                @JoinColumn(name = "employee_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private List<Authority> authorities;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "employee")
    private List<Brand> brands;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "employee")
    private List<Customer> customers;

    @Column(name = "token_reset_password")
    private String tokenResetPassword;

    @Column(name = "last_reset_password_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastResetPasswordDate;

    public Employee() {
    }
}
