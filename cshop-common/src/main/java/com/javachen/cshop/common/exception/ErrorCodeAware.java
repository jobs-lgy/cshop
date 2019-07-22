package com.javachen.cshop.common.exception;

public interface ErrorCodeAware {
    public int getCode();

    public String getMessage();

    public ErrorCodeAware setMessage(String message);
}
