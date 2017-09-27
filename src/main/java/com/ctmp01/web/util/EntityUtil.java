package com.ctmp01.web.util;

import java.util.Collection;

/**
 * 实体
 * 
 * @author PzC.
 * @time 2017年1月6日 下午2:26:13
 * 
 */
public class EntityUtil {
	/**
	 * 单参数处理
	 * @param <T>
	 * 
	 * @param param
	 * @return
	 */
	public static <T> String processSingleParam(String param) {
		Collection<Object> collection = JsonUtil.toMap(param).values();
		return StreamUtils.findFirst(collection).toString();
	}
	
	/**
	 * 分页 PageBean
	 * 
	 * @param params
	 * @return
	 *//*
	public static PageBean instanceOfPageBean(Map<String, Object> params) {
		PageBean pageBean = JsonUtil.toObject(params, PageBean.class);
		pageBean.setConditions(params);
		return pageBean;
	}*/
	

/*	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		map.put("pageSize", "123");
		map.put("pageNumber", "123");
		map.put("name", "abc");
		System.out.println(JsonUtil.toJson(instanceOfPageBean(map)));
	}*/
}
