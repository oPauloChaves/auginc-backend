package com.opaulochaves.auginc.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import com.opaulochaves.auginc.domain.common.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "users" )
public class User extends BaseEntity {

	private static final long serialVersionUID = -3922754643606382333L;

	@NotEmpty
	@Column( nullable = false, unique = true )
	private String email;

	@NotEmpty
	@Column( name = "first_name", nullable = false )
	private String firstName;

	@NotEmpty
	@Column( name = "last_name", nullable = false )
	private String lastName;

	@NotEmpty
	@Column( nullable = false )
	private String password;

	@Column
	private Boolean deleted = false;

	@Transient
	private String getFullName() {
		return getFirstName() + " " + getLastName();
	}
}
