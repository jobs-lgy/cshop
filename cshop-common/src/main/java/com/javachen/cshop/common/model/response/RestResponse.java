package com.javachen.cshop.common.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author june
 * @createTime 2019-07-25 16:00
 * @see
 * @since
 */
@Data
@AllArgsConstructor
public class RestResponse<T> {
    private boolean success;
    private T data;

    public static RestResponse ok(){
        return new RestResponse(true,"ok");
    }

    public static RestResponse success(Object object){
        return new RestResponse(true,object);
    }

    public static RestResponse fail(Object object){
        return new RestResponse(false,object);
    }
}
