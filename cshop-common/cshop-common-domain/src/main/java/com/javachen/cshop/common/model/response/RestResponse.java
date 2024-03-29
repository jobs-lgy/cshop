package com.javachen.cshop.common.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javachen.cshop.common.exception.ErrorCodeAware;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author june
 * @createTime 2019-07-25 16:00
 * @see
 * @since
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse<T> implements Serializable {
    /**
     * 状态码
     */
    private int code;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回对象
     */
    private T data;

    public RestResponse(ErrorCodeAware errorCodeAware) {
        super();
        this.code = errorCodeAware.getCode();
        this.message = errorCodeAware.getMessage();
    }

    public RestResponse(ErrorCodeAware errorCodeAware, String message) {
        super();
        this.code = errorCodeAware.getCode();
        this.message = message;
    }

    public static RestResponse ok() {
        return new RestResponse(0, "success", null);
    }

    public static RestResponse success() {
        return ok();
    }

    public static RestResponse success(Object object) {
        return new RestResponse(0, "success", object);
    }

    public static RestResponse error(ErrorCodeAware errorCodeAware) {
        return new RestResponse(errorCodeAware);
    }

    public static RestResponse error(ErrorCodeAware errorCodeAware, String message) {
        return new RestResponse(errorCodeAware, message);
    }

    public boolean isSuccess() {
        return code == 0;
    }
}
