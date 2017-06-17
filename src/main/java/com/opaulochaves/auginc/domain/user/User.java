package com.opaulochaves.auginc.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import com.opaulochaves.auginc.core.AbstractEntity;
import com.opaulochaves.auginc.domain.company.Company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "users" )
public class User extends AbstractEntity {

	@Column( name = "created_at" )
	private LocalDateTime createdAt;

	@Column( name = "updated_at" )
	private LocalDateTime updatedAt;

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

	@OneToMany( mappedBy = "user" )
	private List<Company> companies;

	@Transient
	private String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	@PrePersist
	protected void defineCommonProps() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = this.createdAt;
	}

	@PreUpdate
	protected void updateCommonProps() {
		this.updatedAt = LocalDateTime.now();
	}
}
