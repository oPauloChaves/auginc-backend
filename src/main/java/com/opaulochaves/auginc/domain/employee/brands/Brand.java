package com.opaulochaves.auginc.domain.employee.brands;

import com.opaulochaves.auginc.domain.common.AbstractEntity;
import com.opaulochaves.auginc.domain.employee.Employee;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "brands", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "employee_id"})})
public class Brand extends AbstractEntity {

    @NotEmpty
    private String name;

    @Column
    @DecimalMin("0")
    private BigDecimal commission;

    @ManyToOne
    private Employee employee;

}
