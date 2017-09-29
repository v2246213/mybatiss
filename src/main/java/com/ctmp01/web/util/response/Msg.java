package com.ctmp01.web.util.response;


import com.ctmp01.web.util.constants.RESPONSE;

import java.io.Serializable;

/**
 * 消息提示类
 * 
 * @author lxy 创建时间：2016年11月11日
 */
public class Msg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 293125379495696075L;

	private Object code = RESPONSE.SUCCESS;;
	private boolean isSuccess = true;
	private String msg;
	private Object data;

	public static Msg makeMsg() {
		Msg msg = new Msg();
		return msg;
	}
	public static Msg makeSuccessMsg() {
		Msg msg = new Msg();
		msg.setSuccess(true);
		return msg;
	}
	public static Msg makeErrorMsg() {
		Msg msg = new Msg();
		msg.setSuccess(false);
		return msg;
	}

	public static Msg makeMsg(String code, String msg) {
		Msg msgOb = new Msg();
		msgOb.setMsg(msg);
		msgOb.setCode(code);
		return msgOb;
	}

	public Object getCode() {
		return code;
	}

	public void setCode(Object code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
