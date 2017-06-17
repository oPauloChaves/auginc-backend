package com.opaulochaves.auginc.domain.company;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;

import com.opaulochaves.auginc.domain.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "companies" )
public class Company {

	@Id
	@SequenceGenerator( name = "companies_generator", sequenceName = "companies_sequence", allocationSize = 1 )
	@GeneratedValue( generator = "companies_generator" )
	private Long id;

	@Version
	@Column
	private Integer version;

	@Column( name = "created_at" )
	private Date createdAt;

	@Column( name = "updated_at" )
	private Date updatedAt;

	@NotEmpty
	@Column( nullable = false )
	private String name;

	@Column
	private Float commission;

	@ManyToOne
	private User user;
	
	@Column
	private Boolean deleted = false;

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
