package com.opaulochaves.auginc.domain.employee;

/**
 * This exception is thrown when a employee entry is not found by
 * using the given id.
 *
 * @author Paulo Chaves
 */
public class EmployeeNotFoundException extends RuntimeException {

    private final Long id;
    
    public EmployeeNotFoundException(Long id) {
        super();
        this.id = id;
    }

    public EmployeeNotFoundException() {
        this.id = null;
    }

    public Long getId() {
        return id;
    }
}
