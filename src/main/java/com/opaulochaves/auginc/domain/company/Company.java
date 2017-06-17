package com.opaulochaves.auginc.domain.company;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.opaulochaves.auginc.core.AbstractEntity;
import com.opaulochaves.auginc.domain.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "companies" )
public class Company extends AbstractEntity {

	@Column( name = "created_at" )
	private LocalDateTime createdAt;

	@Column( name = "updated_at" )
	private LocalDateTime updatedAt;

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
		this.createdAt = LocalDateTime.now();
		this.updatedAt = this.createdAt;
	}

	@PreUpdate
	protected void updateCommonProps() {
		this.updatedAt = LocalDateTime.now();
	}

}
