package com.ctmp01.web.util.enums;

import com.ctmp01.web.util.PropertiesUtil;
import com.ctmp01.web.util.constants.RESPONSE;



/**
 * @describe 请求状态码
 * @date 2017年1月15日
 * @version 1.0.0
 */
public enum ResponseEnum {
	SUCCESS(RESPONSE.SUCCESS),// 请求成功
	ERROR(RESPONSE.ERROR);// 请求失败

    /**
     * 编码
     */
    private int value;
    /**
     * 描述
     */
    private String desc;

    // 构造方法
    private ResponseEnum(int value) {
        this.value = value;
        this.desc = PropertiesUtil.getValue("api_code.properties");
    }



	public int getValue() {
		return value;
	}
	public String getDesc() {
		return desc;
	}
	/**
     * 获取枚举
     * @param value
     * @return
     */
    public static ResponseEnum getByValue(int value) {  
        for (ResponseEnum e : values()) {  
            if (e.getValue() == value) {  
                return e;  
            }  
        }  
        return null;  
    } 
}
