package com.opaulochaves.auginc.domain.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opaulochaves.auginc.domain.employee.authorities.Authority;
import com.opaulochaves.auginc.domain.employee.brands.Brand;
import com.opaulochaves.auginc.domain.employee.customers.Customer;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;

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

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "employee")
    @JsonIgnore
    private List<Brand> brands;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "employee")
    private List<Customer> customers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_authority",
            joinColumns = {
                @JoinColumn(name = "employee_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private List<Authority> authorities;

    private boolean deleted;
    
    @JsonIgnore
    @Column(name = "token_reset_password")
    private String tokenResetPassword;

    @JsonIgnore
    @Column(name = "last_reset_password_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastResetPasswordDate;

    public String fullName() {
        return getFirstName() + " " + getLastName();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", version=" + version + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", title=" + title + ", deleted=" + deleted + '}';
    }

}
