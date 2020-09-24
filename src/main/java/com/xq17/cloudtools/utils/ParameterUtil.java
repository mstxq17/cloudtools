package com.xq17.cloudtools.utils;

import java.util.Map;

/**
 * 
    * @ClassName: ParameterUtil  
    * @Description: 参数格式化
    * @author xq17  
    * @date 2020年9月3日  
    *
 */
public class ParameterUtil {
	
	/**
	 * 
	    * @Title: changeFindByPageParam  
	    * @Description: 处理分页查询中参数,重点是limit后必须转整数等一些整数的处理
	    * @param @param map
	    * @param @return    参数  
	    * @return Map<String,Object>    返回类型  
	    * @throws
	 */
	public static Map<String, Object> changeLimitParam(Map<String, Object> map) {
		try {
			if (map.get("limit") != "" && map.get("offset") != "") {
				int limit = Integer.parseInt(String.valueOf(map.get("limit")));
				int offset = Integer.parseInt(String.valueOf(map.get("offset")));
				map.put("limit", limit);
				map.put("offset", offset);
			}
			// 判断设备
			/*
			 * String userAgent = request.getHeader("User-Agent");
			 * if(userAgent.contains("Android") || userAgent.contains("iPhone") ||
			 * userAgent.contains("iPad")) { rows = (int)Math.ceil(rows/3.0)*3; }
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		try {
			int role = Integer.parseInt(String.valueOf(map.get("role")));
			map.put("role", role);
		} catch (Exception e) {
			map.put("role", null);
			// e.printStackTrace();
		}
		return map;
	}

}
