package com.javachen.cshop.common.exception;

import com.google.common.collect.ImmutableMap;
import com.javachen.cshop.common.response.CommonResponse;
import com.javachen.cshop.common.response.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("handleValidationExceptions", ex);
        return handleBindResult(ex.getBindingResult());
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public CommonResponse handleBindExceptions(BindException ex) {
        log.error(ErrorCode.PARAMETER_INVALID_ERROR.getErrorMsg(), ex);

        return handleBindResult(ex.getBindingResult());
    }

    private CommonResponse handleBindResult(BindingResult bindingResult) {
        //name-->用户名不能为空
        List<ImmutableMap> errors = bindingResult
                .getAllErrors().stream()
                .map(err -> ImmutableMap.of(((FieldError) err).getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());

        //{"status":"fail","code":10001,"data":[{"email":"邮箱格式不正确"},{"password":"长度需要在6和25之间"},{"phone":"手机号格式不正确"}]}
        return CommonResponse.error(ErrorCode.PARAMETER_INVALID_ERROR, errors);
    }


    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResponse handleConstraintViolationException(ConstraintViolationException ex) {
        log.error(ErrorCode.PARAMETER_INVALID_ERROR.getErrorMsg(), ex);

        List<ErrorMessage> errors = ex.getConstraintViolations()
                .stream()
                .map(err -> new ErrorMessage(err.getPropertyPath().toString(), err.getMessage()))
                .collect(Collectors.toList());
        return CommonResponse.error(ErrorCode.PARAMETER_INVALID_ERROR, errors);
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResponse<?> handleException(HttpServletRequest req, Exception ex) {
        ErrorCode errorCode = null;

        //业务异常
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            errorCode = (ErrorCode) businessException.getErrorCodeAware();

            log.error(errorCode.getErrorMsg(), ex);
            return CommonResponse.error(errorCode, businessException.getErrorMsg());
        } else {
            errorCode = ExceptionToErrorCodeHelper.getErrorCode(ex);


            log.error(errorCode.getErrorMsg(), ex);
            return CommonResponse.error(errorCode);
        }
    }
}
