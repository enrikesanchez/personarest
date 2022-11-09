package com.ejemplo.demo.exception;

public class PersonaNoEncontradaException extends Exception {

    private static final long serialVersionUID = 4195767788022286501L;

    public PersonaNoEncontradaException() {
        super();
    }

    public PersonaNoEncontradaException(final String message) {
        super(message);
    }

    public PersonaNoEncontradaException(final Throwable cause) {
        super(cause);
    }

    public PersonaNoEncontradaException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PersonaNoEncontradaException(final String message, final Throwable cause,
            final boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

