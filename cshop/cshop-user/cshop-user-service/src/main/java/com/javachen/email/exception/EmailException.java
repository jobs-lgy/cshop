package com.javachen.email.exception;

public class EmailException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailException() {
        super();
    }

    public EmailException(String message, Throwable arg1) {
        super(message, arg1);
    }

    public EmailException(String message) {
        super(message);
    }

    public EmailException(Throwable arg0) {
        super(arg0);
    }
}
