package com.javachen.cshop.common.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author june
 * @createTime 2019-07-21 14:35
 * @see
 * @since
 */
@Data
@Slf4j
public class ExceptionResponse {
    private int code;  //错误码
    private String message; //提示信息
    private Object detail; //详细异常提示信息
    private final Long timestamp = System.currentTimeMillis();

    public ExceptionResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ExceptionResponse(int code, String message, Object detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public ExceptionResponse(ErrorCodeAware errorCodeAware, String message, Object detail) {
       this(errorCodeAware.getCode(),message,detail);
    }

    public ExceptionResponse(ErrorCodeAware errorCodeAware, Object detail) {
        this(errorCodeAware.getCode(),errorCodeAware.getMessage(),detail);
    }

    public ExceptionResponse(ErrorCodeAware errorCodeAware) {
        this(errorCodeAware.getCode(),errorCodeAware.getMessage(),null);
    }
}
