package com.opaulochaves.auginc.domain.common;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode( of = { "id" } )
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 6048934158080820577L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id", updatable = false, nullable = false )
	private Long id = null;

	@Version
	@Column
	private long version;

	@JsonSerialize( using = LocalDateTimeSerializer.class )
	@Column( name = "created_at" )
	private LocalDateTime createdAt;

	@JsonSerialize( using = LocalDateTimeSerializer.class )
	@Column( name = "updated_at" )
	private LocalDateTime updatedAt;

	@PrePersist
	protected void defineCommonProps() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = this.createdAt;
	}

	protected void updateCommonProps() {
		this.updatedAt = LocalDateTime.now();
	}
}
