package com.javachen.cshop.common.exception;

import lombok.Getter;

/**
 * //包装器业务异常类实现
 */
@Getter
public class BusinessException extends RuntimeException implements ErrorCodeAware {

  private ErrorCodeAware errorCodeAware;

  //直接接收EmBusinessError的传参用于构造业务异常
  public BusinessException(ErrorCodeAware errorCodeAware){
    super(errorCodeAware.getErrorMsg());
    this.errorCodeAware = errorCodeAware;
  }

  //接收自定义errorMsg的方式构造业务异常
  public BusinessException(ErrorCodeAware errorCodeAware, String errorMsg){
    super(errorMsg);
    this.errorCodeAware = errorCodeAware;
    this.errorCodeAware.setErrorMsg(errorMsg);
  }

  @Override
  public int getErrorCode() {
    return this.errorCodeAware.getErrorCode();
  }

  @Override
  public String getErrorMsg() {
    return this.errorCodeAware.getErrorMsg();
  }

  @Override
  public ErrorCodeAware setErrorMsg(String errorMsg) {
    this.errorCodeAware.setErrorMsg(errorMsg);
    return this;
  }

  public ErrorCodeAware getErrorCodeAware() {
    return errorCodeAware;
  }
}
