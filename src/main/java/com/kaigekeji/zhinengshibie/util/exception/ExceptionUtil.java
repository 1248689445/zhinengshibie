package com.kaigekeji.zhinengshibie.util.exception;

import com.kaigekeji.zhinengshibie.util.DateUtil;

import java.io.PrintWriter;
import java.io.StringWriter;


/** 
 * 描述：异常工具类  <br>
 */
public class ExceptionUtil {

	/**
	 * 格式化异常信息
	 * @param exception {@link Exception} 需要格式化的异常
	 * @return {@link String} 格式化后的异常信息
	 */
	public static String conversionFormat(Exception exception) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(DateUtil.getNow());
			sb.append("\r\n异常详情：");
			StringWriter sw = new StringWriter();
			exception.printStackTrace(new PrintWriter(sw,false));
			String exceptionInfo = sw.toString();
			sb.append(exceptionInfo);
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}
	
}
