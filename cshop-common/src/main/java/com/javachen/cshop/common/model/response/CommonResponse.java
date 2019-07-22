package com.javachen.cshop.common.model.response;

import lombok.Data;

/**
 * @param <T>
 */
@Data
public class CommonResponse<T> {
    private boolean success;
    private T data;

    private CommonResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public static CommonResponse success() {
        return success(null);
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<T>(true, data);
    }

    public static <T> CommonResponse<T> error(boolean success, T data) {
        return new CommonResponse<T>(success, data);
    }

    public static <T> CommonResponse<T> error(T data){
        return error(false,data);
    }

}
