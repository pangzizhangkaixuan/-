package com.zkx.cn.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * map工具类
 * @author 张凯旋
 *
 */
public class MapUtil {

	public static Map<String, Object> objectToMap(Object bean) throws Exception{
		if (bean == null) {
			return null;
		}
		
		Map<String, Object> map = new HashMap<>();
		Field[] fields = bean.getClass().getDeclaredFields();//获取对象中的全部属性
		
		//将获取到的属性循环添加到map中
		for (Field field : fields) {
			//设置访问权限以获取所有属性值
			field.setAccessible(true);
			map.put(field.getName(), field.get(bean));
		}
		
		return map;
		
	}
}
