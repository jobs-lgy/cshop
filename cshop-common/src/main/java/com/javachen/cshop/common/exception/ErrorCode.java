package com.javachen.cshop.common.exception;

public enum ErrorCode implements ErrorCodeAware {
    SYSTEM_INTERNAL_ERROR(10001, "系统内部异常"),
    DATA_PARAMETER_INVALID_ERROR(10001, "数据参数不合法"),
    PARAMETER_INVALID_ERROR(10001, "参数不合法"),
    REQUEST_INVALID_ERROR(10002, "不支持的请求类型"),
    UNAUTHORIZED(10002, "未授权访问"),
    AUTHORIZED_FAIL(10002, "授权失败"),

    NOT_FOUND(2000,"记录不存在"),

    USER_TOKEN_INVALID(20001, "用户TOKEN失效"),
    USER_NOT_EXIST(20002, "用户不存在"),
    USER_USERNAME_ALREADY_EXIST(20003, "用户名重复"),
    USER_PHONE_ALREADY_EXIST(20003, "手机号重复"),
    USER_EMAIL_ALREADY_EXIST(20003, "邮箱重复"),

    USER_NOT_ACTIVE(20005, "用户未激活"),

    USER_LOGIN_ERROR(20004, "用户名或密码错误"),
    USER_PASSWORD_ERROR(20004, "密码错误"),
    USER_PASSWORD_NOT_EQUAL(20006, "两次输入密码不一样"),

    RRAND_NOT_EXIST(30001, "品牌不存在"),
    CART_NOT_EXIST(30002, "购物车不存在"),
    CATEGORY_NOT_EXIST(30003, "分类不存在"),
    CATEGORY_PARENT_ID_EXIST(30004, "父分类存在，不能删除"),

    SPU_NOT_EXIST(30005, "商品不存在"),
    SKU_NOT_EXIST(30006, "商品不存在"),
    SPEC_NOT_EXIST(30007, "规格参数不存在"),

    STOCK_NOT_ENOUGH(30007, "库存不足"),

    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
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
