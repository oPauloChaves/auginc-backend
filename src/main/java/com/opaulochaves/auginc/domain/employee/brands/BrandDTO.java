package com.opaulochaves.auginc.domain.employee.brands;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Paulo Chaves
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BrandDTO {

    private Long id;

    @NotEmpty
    @Size(max = 255)
    private String name;

    @DecimalMin("0")
    private BigDecimal commission;

    @NotNull
    @JsonProperty("employee_id")
    private Long employeeID;

}
