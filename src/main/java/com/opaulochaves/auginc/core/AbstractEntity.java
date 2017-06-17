package com.opaulochaves.auginc.core;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.hateoas.Identifiable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Base class for entity implementations. Uses a {@link Long} id.
 */
@MappedSuperclass
@Getter
@ToString
@EqualsAndHashCode
public class AbstractEntity implements Identifiable<Long> {

	private final @Id @GeneratedValue( strategy = GenerationType.AUTO ) @JsonIgnore Long id;
	private @Version Integer version;

	protected AbstractEntity() {
		this.id = null;
	}
}
