package com.opaulochaves.auginc.web.error;

import org.springframework.util.Assert;

/**
 * This class contains the information of a single field error.
 *
 * @author Petri Kainulainen https://github.com/pkainulainen/spring-data-jpa-examples
 */
final class FieldErrorDTO {

    private final String field;

    private final String message;

    FieldErrorDTO(String field, String message) {
        Assert.hasLength(field, "Field cannot be null or empty.");
        Assert.hasLength(message, "Message cannot be null or empty.");

        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}