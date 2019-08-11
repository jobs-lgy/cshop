package com.javachen.cshop.common.exception;

public interface ErrorCodeAware {
    int getCode();

    String getMessage();

    ErrorCodeAware setMessage(String message);
}
