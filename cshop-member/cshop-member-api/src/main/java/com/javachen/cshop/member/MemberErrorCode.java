package com.javachen.cshop.member;

import com.javachen.cshop.common.exception.ErrorCodeAware;

public enum MemberErrorCode implements ErrorCodeAware {

    PHONE_IS_EMPTY(20004, "手机号码不能为空"),
    PHONE_IS_INVALID(20004, "手机号码不合法"),
    VERIFY_CODE_IS_EMPTY(20004, "验证码不能为空"),
    VERIFY_CODE_IS_EXPIRED(20004, "验证码已过期"),
    VERIFY_CODE_IS_WRONG(20004, "验证码错误"),
    ;

    private int code;
    private String message;

    MemberErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorCodeAware setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
