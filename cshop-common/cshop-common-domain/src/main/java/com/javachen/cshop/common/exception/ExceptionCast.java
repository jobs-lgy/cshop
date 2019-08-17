package com.javachen.cshop.common.exception;

public class ExceptionCast {

    public static void cast(ErrorCodeAware errorCodeAware){
        throw new CustomException(errorCodeAware);
    }

    public static void cast(ErrorCodeAware errorCodeAware, String message){
        throw new CustomException(errorCodeAware,message);
    }
}
