package com.javachen.common.response;

import com.javachen.common.exception.ErrorCode;
import lombok.Data;

@Data
public class CommonResponse<T> {
	private String status;
	private String message;
	private Integer code;
	private T data;

	public CommonResponse(){
	}

	public CommonResponse(String status,Integer code, String message) {
		this.status = status;
		this.message = message;
		this.code = code;
	}
	public CommonResponse(String status, Integer code, T data) {
		this.status = status;
		this.code = code;
		this.data = data;
	}

	public CommonResponse(String status,Integer code, String message,T data) {
		this.status = status;
		this.message = message;
		this.code = code;
		this.data = data;
	}


	public static <T> CommonResponse<T> ok(){
		return success();
	}

	public static <T> CommonResponse<T> ok(T data){
		return success(data);
	}
	
	public static <T> CommonResponse<T> success(){
		return success(null);
	}
	
	public static <T> CommonResponse<T> success(T data){
		return new CommonResponse<T>("success",0,data);
	}
	
	public static <T> CommonResponse<T> error(ErrorCode errorCode,String message,T data){
		return new CommonResponse<T>("fail",errorCode.getErrorCode(),message,data);
	}

	public static <T> CommonResponse<T> error(ErrorCode errorCode,T data){
		return error(errorCode, errorCode.getErrorMsg(),null);
	}

	public static <T> CommonResponse<T> error(ErrorCode errorCode,String message){
		return error(errorCode, message,null);
	}

	public static <T> CommonResponse<T> error(ErrorCode errorCode){
		return error(errorCode, errorCode.getErrorMsg(),null);
	}
}
