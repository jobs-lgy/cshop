package com.javachen.cshop.common.exception;

import com.google.common.collect.ImmutableMap;
import com.javachen.cshop.common.model.response.CommonResponse;
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
 * @author june
 * @createTime 2019-06-24 22:02
 * @see
 * @since
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse handleValidationExceptions(HttpServletRequest request,MethodArgumentNotValidException ex) {
        log.error("uri=[{}],message=[{}]",request.getRequestURI(),ExceptionUtils.getRootCause(ex).getMessage(),ex);

        return handleBindResult(ex.getBindingResult());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BindException.class)
    public CommonResponse handleBindExceptions(HttpServletRequest request,BindException ex) {
        log.error("uri=[{}],message=[{}]",request.getRequestURI(),ExceptionUtils.getRootCause(ex).getMessage(),ex);

        return handleBindResult(ex.getBindingResult());
    }

    private CommonResponse handleBindResult(BindingResult bindingResult) {
        //name-->用户名不能为空
        List<ImmutableMap> errors = bindingResult
                .getAllErrors().stream()
                .map(err -> ImmutableMap.of(((FieldError) err).getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());

        return CommonResponse.error(new ExceptionResponse(ErrorCode.PARAMETER_INVALID_ERROR, errors));
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResponse<ExceptionResponse> handleConstraintViolationException(HttpServletRequest request,ConstraintViolationException ex) {
        log.error("uri=[{}],message=[{}]",request.getRequestURI(),ExceptionUtils.getRootCause(ex).getMessage(),ex);

        List<ImmutableMap> errors = ex.getConstraintViolations()
                .stream()
                .map(err -> ImmutableMap.of(err.getPropertyPath().toString(),err.getMessage()))
                .collect(Collectors.toList());
        return CommonResponse.error(new ExceptionResponse(ErrorCode.DATA_PARAMETER_INVALID_ERROR, errors));
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public CommonResponse<ExceptionResponse> handleException(HttpServletRequest request, Exception ex) {
        ErrorCode  errorCode = ExceptionToErrorCodeHelper.getErrorCode(ex);
        String message = ExceptionUtils.getRootCause(ex).getMessage(); //最初的异常原因
        log.error("uri=[{}],message=[{}]",request.getRequestURI(),ex);
        return CommonResponse.error(new ExceptionResponse(errorCode, message));
    }
}
