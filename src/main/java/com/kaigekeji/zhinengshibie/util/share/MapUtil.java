package com.kaigekeji.zhinengshibie.util.share;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：Map工具类
 */
public class MapUtil {
	
	/**
	 * 该函数限制用户主动创建实例
	 */
	private MapUtil() {}
	
	/**
	 * 获取Map实例
	 * @return {@link Map} Map实例
	 */
	public static Map<String, Object> getMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}

}
