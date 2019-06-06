package com.kaigekeji.zhinengshibie.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 载体类读写数据帮助类 Created by wl on 2016年8月14日.
 */
@SuppressWarnings("all")
public class JSONBeanUtil {
	/**
	 * 将map数据写入obj对象
	 * 
	 * @param obj
	 *            对象
	 * @param map
	 *            数据集
	 */
	public static <T> Object writeMethod(Object obj, Map<String, T> map) {
		for (Entry<String, T> entry : map.entrySet()) {
			try {
				PropertyDescriptor pd = new PropertyDescriptor(entry.getKey(), obj.getClass());
				Method method = pd.getWriteMethod();
				method.invoke(obj, get(map, entry.getKey()));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return obj;
	}

	/**
	 * 读取obj对象数据到map数据集
	 * 
	 * @param obj
	 *            对象
	 * @param map
	 *            数据集
	 */
	public static Map readMethod(Map map, Object obj) {
		for (Field field : obj.getClass().getDeclaredFields()) {
			try {
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), obj.getClass());
				Method method = pd.getReadMethod();
				Object obj2 = method.invoke(obj);
				map.put(field.getName(), obj2);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return map;
	}

	/**
	 * 请求参数map集合转普通map集合（key转成小写）
	 * 
	 * @param map
	 *            参数map集合
	 * @return 普通map集合
	 */
	public static <T> Map getMap(Map<String, T> param) {
		Set<String> keys = param.keySet();
		return getMap(param, keys.toArray(new String[keys.size()]));
	}

	/**
	 * 获取map集合部分数据
	 * 
	 * @param param
	 *            map集合
	 * @param keys
	 *            map集合key
	 * @return
	 */
	public static <T> Map getMap(Map<String, T> param, String... keys) {
		return getMap(param, keys, keys);
	}

	/**
	 * 获取map集合部分数据
	 * 
	 * @param param
	 *            map集合
	 * @param keys
	 *            原map集合key
	 * @param keys2
	 *            新map集合的key
	 * @return
	 */
	public static <T> Map getMap(Map<String, T> param, String[] keys, String... keys2) {
		Map map = new HashMap();
		try {
			for (int i = 0; i < keys2.length; ++i) {
				map.put(keys2[i], get(param, keys[i]));
			}
			for (int i = keys2.length; i < keys.length; ++i) {
				map.put(keys[i], get(param, keys[i]));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 获取request参数map数据
	 * 
	 * @param map
	 *            request参数map
	 * @param key
	 *            键
	 * @return 数据
	 */
	public static <T> Object get(Map<String, T> map, String key) {
		Object[] objs = toArray(map.get(key));
		if (objs.length == 1) {
			return objs[0];
		} else {
			return (Object) objs;
		}
	}

	/**
	 * 对象转换成数组
	 * 
	 * @param obj
	 *            对象
	 * @return 数组
	 */
	public static Object[] toArray(Object obj) {
		if (obj != null) {
			if (obj instanceof Collection) {
				Collection coll = (Collection) obj;
				return coll.toArray();
			}
			if (obj.getClass().isArray()) {
				return (Object[]) obj;
			}
		}
		return new Object[] { obj };
	}

	/**
	 * map集合转json字符串
	 * 
	 * @param json
	 *            map集合
	 * @return
	 */
	public static <T> String toJSONString(Map<String, T> json) {
		return JSONObject.toJSONString(json);
	}

	/**
	 * 数组转json字符串
	 * 
	 * @param json
	 *            数组
	 * @return
	 */
	public static String toJSONString(Object[] json) {
		return JSONArray.toJSONString(json);
	}
	
	/**
	 * 集合转json字符串
	 * 
	 * @param json
	 *            集合
	 * @return
	 */
	public static String toJSONString(Collection json) {
		return JSONArray.toJSONString(json);
	}
	
	/**
	 * json字符串解析成map集合
	 * 
	 * @param json
	 *            字符串
	 * @return
	 */
	public static Map parseObject(String json) {
			return JSONObject.parseObject(json);
	}




	/**
	 * json字符串解析成数组
	 * 
	 * @param json
	 *            字符串（数组格式）
	 * @return
	 */
	public static List<Object> parseArray(String json) {
		return JSONArray.parseArray(json);
	}

}
