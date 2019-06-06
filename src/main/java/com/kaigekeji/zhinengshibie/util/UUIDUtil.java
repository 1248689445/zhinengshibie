package com.kaigekeji.zhinengshibie.util;

import java.util.UUID;

/**
 * 描述：UUID工具类 <br>
 */
public class UUIDUtil {
	
	private UUIDUtil() {}
	
	/**
	 * 获取UUID，去除“-”
	 * @return {@link String} UUID
	 */
	public static final String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
