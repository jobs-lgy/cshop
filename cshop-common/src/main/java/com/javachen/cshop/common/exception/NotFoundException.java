package com.javachen.cshop.common.exception;

import lombok.Getter;

/**
 * //包装器业务异常类实现
 */
@Getter
public class NotFoundException extends RuntimeException implements ErrorCodeAware {

    private ErrorCodeAware errorCodeAware;

    //接收自定义errorMsg的方式构造业务异常
    public NotFoundException(String message) {
        super(message);
        this.errorCodeAware = ErrorCode.NOT_FOUND;
        this.errorCodeAware.setMessage(message);
    }

    @Override
    public int getCode() {
        return this.errorCodeAware.getCode();
    }

    @Override
    public String getMessage() {
        return this.errorCodeAware.getMessage();
    }

    @Override
    public ErrorCodeAware setMessage(String message) {
        this.errorCodeAware.setMessage(message);
        return this;
    }

    public ErrorCodeAware getErrorCodeAware() {
        return errorCodeAware;
    }
}
