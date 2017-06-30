package com.opaulochaves.auginc.web.error;

import com.opaulochaves.auginc.domain.common.EntryNotFoundException;
import java.util.List;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class handles the exceptions thrown by our REST API.
 *
 * @author Petri Kainulainen
 * https://github.com/pkainulainen/spring-data-jpa-examples
 */
@ControllerAdvice
@Slf4j
public final class RestErrorHandler {

    private static final String ERROR_CODE_ENTRY_ID_NOT_FOUND = "error.entry.id.not.found";
    private static final String ERROR_CODE_ENTRY_NOT_FOUND = "error.entry.not.found";

    private final MessageSource messageSource;

    @Autowired
    public RestErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * This exception handler <i>should</i> handle violations of unique
     * constraints. TODO: DataIntegrityViolationException is really broad, we need to check
     * somehow that this only gets invoked for unique constraint violations
     *
     * @param e The exception to handle
     * @return An error message
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorDTO handleJpaSystemException(DataIntegrityViolationException e) {
        return new ErrorDTO(HttpStatus.CONFLICT.name(), e.getMostSpecificCause().getMessage());
    }

    /**
     * Processes an error that occurs when the requested entry is not found.
     *
     * @param ex The exception that was thrown when the entry was not found.
     * @param currentLocale The current locale.
     * @return An error object that contains the error code and message.
     */
    @ExceptionHandler(EntryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ErrorDTO handleEntryNotFound(EntryNotFoundException ex, Locale currentLocale) {
        String msg = "Entry was not found";
        if (ex.getId() != null) {
            msg += " by using id: {}";
        }
        LOG.error(msg, ex.getId());

        MessageSourceResolvable errorMessageRequest = createSingleErrorMessageRequest(
                ex.getId() != null ? ERROR_CODE_ENTRY_ID_NOT_FOUND : ERROR_CODE_ENTRY_NOT_FOUND,
                ex.getId()
        );

        String errorMessage = messageSource.getMessage(errorMessageRequest, currentLocale);
        return new ErrorDTO(HttpStatus.NOT_FOUND.name(), errorMessage);
    }

    private DefaultMessageSourceResolvable createSingleErrorMessageRequest(String errorMessageCode, Object... params) {
        return new DefaultMessageSourceResolvable(new String[]{errorMessageCode}, params);
    }

    /**
     * Processes an error that occurs when the validation of an object fails.
     *
     * @param ex The exception that was thrown when the validation failed.
     * @return An error object that describes all validation errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO handleValidationErrors(MethodArgumentNotValidException ex, Locale currentLocale) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        LOG.error("Found {} validation errors", fieldErrors.size());

        return constructValidationErrors(fieldErrors, currentLocale);
    }

    private ValidationErrorDTO constructValidationErrors(List<FieldError> fieldErrors, Locale currentLocale) {
        ValidationErrorDTO dto = new ValidationErrorDTO();

        for (FieldError fieldError : fieldErrors) {
            String localizedErrorMessage = getValidationErrorMessage(fieldError, currentLocale);
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }

        return dto;
    }

    private String getValidationErrorMessage(FieldError fieldError, Locale currentLocale) {
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
//        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {;
//            String[] fieldErrorCodes = fieldError.getCodes();
//            localizedErrorMessage = fieldErrorCodes[0];
//        }
        return localizedErrorMessage;
    }
}
