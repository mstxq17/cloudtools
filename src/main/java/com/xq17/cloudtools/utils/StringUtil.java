package com.xq17.cloudtools.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
    * @ClassName: StringUtil  
    * @Description: 字符串工具类
    * @author xq17  
    * @date 2020年9月3日  
    *
 */
public class StringUtil {
	
	/**
	 * 
	    * @Title: checkStringNull  
	    * @Description: 检查字符串是否为空  
	    * @param @param strs
	    * @param @return    参数  
	    * @return boolean    返回类型  
	    * @throws
	 */
	public static boolean checkStringNull(String ... strs) {
		if(strs == null || strs.length <= 0)
		{
			return true;
		}
		for(String str:strs) {
			if(str == null || "".equals(str)){
				return true;
			}
		}
		return false;
	}

	public static boolean isOkName(String name) {
		final String format = "[^\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w-_.]";
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(name);
		return !matcher.find();
	}
}
