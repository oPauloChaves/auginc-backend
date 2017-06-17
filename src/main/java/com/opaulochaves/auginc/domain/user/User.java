package com.opaulochaves.auginc.domain.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;

import com.opaulochaves.auginc.domain.company.Company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "users" )
public class User implements Serializable {

	private static final long serialVersionUID = -7524551873023594098L;

	@Id
	@SequenceGenerator( name = "users_generator", sequenceName = "users_sequence", allocationSize = 1 )
	@GeneratedValue( generator = "users_generator" )
	private Long id;

	@Version
	@Column
	private Integer version;

	@Column( name = "created_at" )
	private Date createdAt;

	@Column( name = "updated_at" )
	private Date updatedAt;

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
		this.createdAt = new Date();
		this.updatedAt = this.createdAt;
	}

	@PreUpdate
	protected void updateCommonProps() {
		this.updatedAt = new Date();
	}
}
