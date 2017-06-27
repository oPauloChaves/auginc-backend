package com.opaulochaves.auginc.domain.employee;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Getter
@Setter
@Table( uniqueConstraints = { @UniqueConstraint(columnNames = {"type", "employee_id"}) } )
public class Settings {

    public enum SettingsType {
        LOCALE, WEBTHEME
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SettingsType type;

    @NotEmpty
    private String value;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "employee_id")
    private Employee employee;

}