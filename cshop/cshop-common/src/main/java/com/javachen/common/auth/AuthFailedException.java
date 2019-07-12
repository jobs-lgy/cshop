package com.javachen.common.auth;

/**
 * @author june
 * @createTime 2019-06-28 10:55
 * @see
 * @since
 */
public class AuthFailedException extends RuntimeException {
    public AuthFailedException() {
        super();
    }

    public AuthFailedException(String message) {
        super(message);
    }

    public AuthFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthFailedException(Throwable cause) {
        super(cause);
    }

    protected AuthFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
