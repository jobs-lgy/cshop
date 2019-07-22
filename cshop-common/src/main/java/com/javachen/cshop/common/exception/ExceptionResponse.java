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
    private int status; //状态码
    private String message; //错误信息
    private Object detail; //详细异常提示信息
    private final Long timestamp = System.currentTimeMillis();

    public ExceptionResponse(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public ExceptionResponse(int status,int code, String message, Object detail) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public ExceptionResponse(int status,ErrorCodeAware errorCodeAware, String message, Object detail) {
       this(status,errorCodeAware.getCode(),message,detail);
    }

    public ExceptionResponse(int status,ErrorCodeAware errorCodeAware, Object detail) {
        this(status,errorCodeAware.getCode(),errorCodeAware.getMessage(),detail);
    }

    public ExceptionResponse(int status,ErrorCodeAware errorCodeAware) {
        this(status,errorCodeAware.getCode(),errorCodeAware.getMessage(),null);
    }

    public ExceptionResponse(ErrorCodeAware errorCodeAware) {
        this(500,errorCodeAware.getCode(),errorCodeAware.getMessage(),null);
    }

    public ExceptionResponse(ErrorCodeAware errorCodeAware, Object detail) {
        this(500,errorCodeAware.getCode(),errorCodeAware.getMessage(),detail);
    }
}
