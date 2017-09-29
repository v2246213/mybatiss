package com.ctmp01.web.util.response;

import java.io.Serializable;

import com.ctmp01.web.util.JsonUtil;
import com.ctmp01.web.util.constants.RESPONSE;
import com.ctmp01.web.util.enums.ResponseEnum;


/**
 * 返回实体
 *
 */
public class ApiResult implements Serializable {
	private static final long serialVersionUID = 1853785175677090662L;

	private int code = ResponseEnum.SUCCESS.getValue();
	private String message = ResponseEnum.SUCCESS.getDesc();
	private Object data;

	public ApiResult() {
	}

	public ApiResult(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ApiResult(Object data) {
		this.data = data;
	}

	public ApiResult(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public ApiResult(ResponseEnum responseEnum) {
		this.code = responseEnum.getValue();
		this.message = responseEnum.getDesc();
	}

	public ApiResult(ResponseEnum responseEnum, Object data) {
		this.code = responseEnum.getValue();
		this.message = responseEnum.getDesc();
		this.data = data;
	}
	public void setEnum(ResponseEnum responseEnum, Object data) {
		this.code = responseEnum.getValue();
		this.message = responseEnum.getDesc();
		this.data = data;
	}
	public void setResponseEnum(ResponseEnum responseEnum) {
		this.code = responseEnum.getValue();
		this.message = responseEnum.getDesc();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static void main(String[] args) {
		System.out.println(JsonUtil.toJson(new ApiResult(RESPONSE.ERROR, "失败")));
	}
}
