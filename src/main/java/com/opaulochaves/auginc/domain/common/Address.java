package com.opaulochaves.auginc.domain.common;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author paulo
 */
@Getter
@Setter
@Embeddable
@Access(AccessType.FIELD)
public class Address {

    @NotEmpty
    @Column(name = "street_addres1")
    private String street;

    @Column(name = "street_addres2")
    private String street2;

    @NotEmpty
    @Column(name = "number")
    private String houseNumber;

    @NotEmpty
    private String zipCode;

    @NotEmpty
    private String city;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private FederalState federalState;

    @NotEmpty
    private String country;

}
