package com.opaulochaves.auginc.domain.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.opaulochaves.auginc.domain.common.BaseEntity;
import com.opaulochaves.auginc.domain.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "company" )
public class Company extends BaseEntity {

	private static final long serialVersionUID = -2864789602012466282L;

	@Column( unique = true, nullable = false )
	private String name;

	@Column
	private Double commission;

	@ManyToOne
	private User user;
}
