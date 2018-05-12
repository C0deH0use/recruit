package com.code.house.recruit.domain.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(final String message) {
        super(message);
    }
}
