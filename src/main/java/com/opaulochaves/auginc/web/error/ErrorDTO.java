package com.opaulochaves.auginc.web.error;

import org.springframework.util.Assert;

/**
 * This class contains the information of an error that occurred when the API
 * tried to perform the operation requested by the client.
 *
 * @author Petri Kainulainen https://github.com/pkainulainen/spring-data-jpa-examples
 */
final class ErrorDTO {

    private final String code;
    private final String message;

    ErrorDTO(String code, String message) {
        Assert.hasLength(code, "Code cannot be null or empty.");
        Assert.hasLength(message, "Message cannot be null or empty.");

        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
