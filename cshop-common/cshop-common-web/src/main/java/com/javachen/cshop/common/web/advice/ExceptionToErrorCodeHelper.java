package com.javachen.cshop.common.web.advice;

import com.google.common.collect.ImmutableMap;
import com.javachen.cshop.common.exception.CustomException;
import com.javachen.cshop.common.exception.ErrorCode;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

public class ExceptionToErrorCodeHelper {
    private static final ImmutableMap<Class<? extends Throwable>, ErrorCode> MAP = ImmutableMap.<Class<? extends Throwable>, ErrorCode>builder()
            .put(HttpMediaTypeNotSupportedException.class, ErrorCode.REQUEST_INVALID_ERROR)
            .put(HttpRequestMethodNotSupportedException.class, ErrorCode.REQUEST_INVALID_ERROR)
            .put(IllegalStateException.class, ErrorCode.PARAMETER_INVALID_ERROR)
            .put(NumberFormatException.class, ErrorCode.PARAMETER_INVALID_ERROR).build();

    public static ErrorCode getErrorCode(Throwable throwable) {
        ErrorCode errorCode = ErrorCode.SYSTEM_INTERNAL_ERROR;
        if (throwable == null) {
            return errorCode;
        }
        if (throwable instanceof CustomException) {
            return (ErrorCode) ((CustomException) throwable).getErrorCodeAware();
        } else {
            errorCode = MAP.get(throwable.getClass());
        }
        //没有定义
        if (errorCode == null) {
            //根异常
            Throwable rootCause = ExceptionUtils.getRootCause(throwable);
            if (!rootCause.equals(throwable) && rootCause != null) {
                errorCode =MAP.get(rootCause.getClass());
            }
        }

        return errorCode == null ? ErrorCode.SYSTEM_INTERNAL_ERROR : errorCode;
    }
}
