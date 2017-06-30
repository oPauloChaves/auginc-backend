package com.opaulochaves.auginc.domain.common;

import java.math.BigDecimal;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
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
    @Column(name = "street_addres")
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

    @NotNull
    @Enumerated(EnumType.STRING)
    private FederalState federalState;

    @NotEmpty
    private String country;
    
    private BigDecimal latitude;
    
    private BigDecimal longitude;

}
