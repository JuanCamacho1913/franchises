package com.example.franchise.domain.exception;

public class ElementNotFoundException extends RuntimeException{

    public ElementNotFoundException() {
        super();
    }

    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementNotFoundException(String message) {
        super(message);
    }

    public ElementNotFoundException(Throwable cause) {
        super(cause);
    }
}
