package com.javachen.cshop.common.web.advice;

import com.google.common.collect.ImmutableMap;
import com.javachen.cshop.common.model.response.RestResponse;
import com.javachen.cshop.common.exception.ErrorCode;
import com.javachen.cshop.common.exception.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
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
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResponse<ExceptionResponse> handleValidationExceptions(HttpServletRequest request, MethodArgumentNotValidException ex) {
        return handleBindResult(request, ex.getBindingResult(), ex);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    public RestResponse<ExceptionResponse> handleBindExceptions(HttpServletRequest request, BindException ex) {
        return handleBindResult(request, ex.getBindingResult(), ex);
    }

    private RestResponse<ExceptionResponse> handleBindResult(HttpServletRequest request, BindingResult bindingResult, Exception ex) {
        ErrorCode errorCode = ExceptionToErrorCodeHelper.getErrorCode(ex);

        //name-->用户名不能为空
        List<ImmutableMap> errors = bindingResult
                .getAllErrors().stream()
                .map(err -> ImmutableMap.of(((FieldError) err).getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());

        log.error("uri=[{}],message=[{}]", request.getRequestURI(), errors, ex);

        return RestResponse.error(errorCode);
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public RestResponse<ExceptionResponse> handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException ex) {
        ErrorCode errorCode = ExceptionToErrorCodeHelper.getErrorCode(ex);

        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(err -> err.getPropertyPath().toString() + err.getMessage())
                .collect(Collectors.toList());

        log.error("uri=[{}],message=[{}]", request.getRequestURI(), errors, ex);

        return RestResponse.error(errorCode);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Exception.class)
    public RestResponse<ExceptionResponse> handleException(HttpServletRequest request, Exception ex) {
        ErrorCode errorCode = ExceptionToErrorCodeHelper.getErrorCode(ex);
        String message = ExceptionUtils.getRootCause(ex).getMessage(); //最初的异常原因
        log.error("uri=[{}],message=[{}]", request.getRequestURI(), ex);
        return RestResponse.error(errorCode, message);
    }
}
