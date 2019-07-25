package com.javachen.cshop.common.exception;

import com.google.common.collect.ImmutableMap;
import com.javachen.cshop.common.model.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 微服务，json全部返回200
 *
 * @author june
 * @createTime 2019-06-24 22:02
 * @see
 * @since
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 用于动态获取配置文件的属性值
    private static final String LOGGING_LEVEL_CSHOP = "logging.level.com.javachen.cshop";

    @Autowired
    private ConfigurableApplicationContext applicationContext;


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResponse<ExceptionResponse> handleValidationExceptions(HttpServletRequest request, MethodArgumentNotValidException ex) {
        log.error("uri=[{}],message=[{}]", request.getRequestURI(), ExceptionUtils.getRootCause(ex).getMessage(), ex);

        return handleBindResult(ex.getBindingResult(), ex);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    public RestResponse<ExceptionResponse> handleBindExceptions(HttpServletRequest request, BindException ex) {
        log.error("uri=[{}],message=[{}]", request.getRequestURI(), ExceptionUtils.getRootCause(ex).getMessage(), ex);

        return handleBindResult(ex.getBindingResult(), ex);
    }

    private RestResponse<ExceptionResponse> handleBindResult(BindingResult bindingResult, Exception ex) {
        //name-->用户名不能为空
        List<ImmutableMap> errors = bindingResult
                .getAllErrors().stream()
                .map(err -> ImmutableMap.of(((FieldError) err).getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());

        String level = applicationContext.getEnvironment().getProperty(LOGGING_LEVEL_CSHOP);
        ExceptionResponse exceptionResponse = ExceptionResponse.withDetail(ErrorCode.PARAMETER_INVALID_ERROR, errors, ex.getStackTrace(), level);
        return RestResponse.fail(exceptionResponse);
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public RestResponse<ExceptionResponse> handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException ex) {
        log.error("uri=[{}],message=[{}]", request.getRequestURI(), ExceptionUtils.getRootCause(ex).getMessage(), ex);

        List<ImmutableMap> errors = ex.getConstraintViolations()
                .stream()
                .map(err -> ImmutableMap.of(err.getPropertyPath().toString(), err.getMessage()))
                .collect(Collectors.toList());

        String level = applicationContext.getEnvironment().getProperty(LOGGING_LEVEL_CSHOP);
        ExceptionResponse exceptionResponse = ExceptionResponse.withDetail(ErrorCode.DATA_PARAMETER_INVALID_ERROR, errors, ex.getStackTrace(), level);

        return RestResponse.fail(exceptionResponse);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Exception.class)
    public RestResponse<ExceptionResponse> handleException(HttpServletRequest request, Exception ex) {
        ErrorCode errorCode = ExceptionToErrorCodeHelper.getErrorCode(ex);
        String message = ExceptionUtils.getRootCause(ex).getMessage(); //最初的异常原因
        log.error("uri=[{}],message=[{}]", request.getRequestURI(), ex);

        String level = applicationContext.getEnvironment().getProperty(LOGGING_LEVEL_CSHOP);
        ExceptionResponse exceptionResponse = ExceptionResponse.withDetail(errorCode, message, ex.getStackTrace(), level);

        return RestResponse.fail(exceptionResponse);
    }
}
