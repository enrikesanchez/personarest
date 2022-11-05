package com.ejemplo.demo.exception;

public class PersonaNoEncontradaException extends Exception {
    private static final long serialVersionUID = 4195767788022286501L;

    public PersonaNoEncontradaException() {
        super();
    }

    public PersonaNoEncontradaException(String message) {
        super(message);
    }

    public PersonaNoEncontradaException(Throwable cause) {
        super(cause);
    }

    public PersonaNoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonaNoEncontradaException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

