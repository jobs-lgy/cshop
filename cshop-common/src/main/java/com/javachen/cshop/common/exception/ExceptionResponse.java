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
    public static final String LOGGER_LEVEL_DEBUG = "DEBUG";

    private int code;  //错误码
    private String message; //提示信息
    private Object errors; //详细信息
    private Object detail; //异常堆栈信息
    private final Long timestamp = System.currentTimeMillis();

    public ExceptionResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ExceptionResponse(int code, String message, Object errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public ExceptionResponse(int code, String message, Object errors, Object detail) {
        this.code = code;
        this.message = message;
        this.errors = errors;
        this.detail = detail;
    }

    public static ExceptionResponse with(ErrorCodeAware errorCodeAware, String message) {
        return new ExceptionResponse(errorCodeAware.getCode(), message);
    }

    public static ExceptionResponse withError(ErrorCodeAware errorCodeAware, String message, Object errors) {
        return new ExceptionResponse(errorCodeAware.getCode(), message, errors);
    }

    public static ExceptionResponse withError(ErrorCodeAware errorCodeAware, Object errors) {
        return new ExceptionResponse(errorCodeAware.getCode(), errorCodeAware.getMessage(), null);
    }

    public static ExceptionResponse with(ErrorCodeAware errorCodeAware) {
        return new ExceptionResponse(errorCodeAware.getCode(), errorCodeAware.getMessage());
    }

    public static ExceptionResponse withDetail(ErrorCodeAware errorCodeAware, Object errors, Object detail,String level) {
        if (LOGGER_LEVEL_DEBUG.equals(level)) {
            return new ExceptionResponse(errorCodeAware.getCode(), errorCodeAware.getMessage(), errors, detail);
        } else {
            return new ExceptionResponse(errorCodeAware.getCode(), errorCodeAware.getMessage(), errors);
        }
    }
}
