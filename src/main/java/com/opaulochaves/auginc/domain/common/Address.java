package com.opaulochaves.auginc.domain.common;

import java.math.BigDecimal;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author paulo
 */
@Getter
@Setter
@Embeddable
@Access(AccessType.FIELD)
public class Address {

    @Column(name = "street_address")
    private String street;

    @Column(name = "street_address2")
    private String street2;

    @Column(name = "place_number")
    private String houseNumber;

    @Column(name = "zipcode")
    private String zipCode;

    private String city;

    @Enumerated(EnumType.STRING)
    @Column(name = "federal_state")
    private FederalState federalState;

    private String country;
    
    private BigDecimal latitude;
    
    private BigDecimal longitude;

}
