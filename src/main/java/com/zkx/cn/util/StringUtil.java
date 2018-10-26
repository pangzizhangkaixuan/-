package com.zkx.cn.util;

/**
 * 字符串工具类
 * @author 张凯旋
 *
 */
public class StringUtil {

	/**
	 * 判断字符串是否为null或空字符串
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank (String str) {
		if (str != null && str.length() > 0 && str.trim().length() > 0) {
			return true;
		}
		return false;
	}
}
