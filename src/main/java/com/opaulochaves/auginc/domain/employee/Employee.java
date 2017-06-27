package com.opaulochaves.auginc.domain.employee;

import com.opaulochaves.auginc.domain.employee.brands.Brand;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

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

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Column(unique = true)
    @Email
    @NotEmpty
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String title;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "employee")
    private List<Brand> brands;

    private boolean deleted;

    public String fullName() {
        return getFirstName() + " " + getLastName();
    }

}
