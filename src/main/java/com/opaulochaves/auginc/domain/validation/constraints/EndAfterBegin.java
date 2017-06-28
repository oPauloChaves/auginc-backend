package com.opaulochaves.auginc.domain.validation.constraints;

import com.opaulochaves.auginc.domain.validation.EndAfterBeginValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;

/**
 * Checks that the begin field is not after the end date, but they may be the same.
 *
 * @author Moritz Schulze (https://github.com/techdev-solutions/trackr-backend)
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EndAfterBeginValidator.class)
public @interface EndAfterBegin {

    Class[] groups() default {};

    Class[] payload() default {};

    String message() default "{validation.date.endAfterBegin}";
    String begin();
    String end();
    @interface List {
        EndAfterBegin[] value();
    }
}