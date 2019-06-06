package com.kaigekeji.zhinengshibie.util.share;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;




/** 
 * 描述：判断相关工具类  <p>
 */
public class EmptyUtil {

	private EmptyUtil() {
        throw new UnsupportedOperationException("你不能实例化我");
    }
		
	/**
     * 一次性判断多个或单个对象为空。
     * @param objects {@link Object}
     * @return 只要有一个元素为空，则返回true
     */
    public static boolean isBlank(Object...objects){
        Boolean result = false;
        for (Object object : objects) {
            if(object == null
            		|| "".equals(object.toString().trim()) 
                    || "null".equals(object.toString().trim())
                    || "[null]".equals(object.toString().trim())
                    || "[]".equals(object.toString().trim())){
                result = true;
                break;
            }
        }
        return result;
    }
    
    /**
     * 判断Map集合中是否有元素的值为空
     * @param map
     * @return
     */
    public static Map<String, String> parseMap(Map<String, Object> map) {
        Map<String, String> mesMap = new HashMap();
        mesMap.put("code", "1");
    	for (Iterator<String> itor = map.keySet().iterator(); itor.hasNext();) {  
        	String key = itor.next();
        	if(null == map.get(key) || "".equals(map.get(key))) {
        		mesMap.put("code", "0");
        		mesMap.put(key, "失败");
        		return mesMap;
        	}
        }  
        return mesMap;
    }
    
    /**
	 * 判断Map集合中是否有空元素
	 * 
	 * @param map {@link Map} Map集合 
	 * @return {@link String} 若集合中有空元素，返回第一个找到的空元素，例：key为空！，没有空元素返回null。
	 */
	public static String findBlank(Map<String, String> map) {
		for (Iterator<String> itor = map.keySet().iterator(); itor.hasNext();) {
			String key = itor.next();
			if (null == map.get(key) || "".equals(map.get(key))) {
				return key + "为空！";
			}
		}
		return null;
	}
	
	
    
    /**
     * 一次性判断多个或单个对象不为空。
     * @param objects {@link Object}
     * @return 只要有一个元素不为空，则返回true
     */
    public static boolean isNotBlank(Object...objects){
        return !isBlank(objects);
    }
    
}
