package com.opaulochaves.auginc.domain.common;

/**
 * This exception is thrown when a entity entry is not found by
 * using the given id or another unique field.
 *
 * @author Paulo Chaves
 */
public class EntryNotFoundException extends RuntimeException {

    private final Long id;
    
    public EntryNotFoundException() {
        super();
        this.id = null;
    }

    public EntryNotFoundException(Long id) {
        this.id = id;
    }

    public EntryNotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }
    
    public EntryNotFoundException(String message) {
        super(message);
        this.id = null;
    }

    public Long getId() {
        return id;
    }
    
}
