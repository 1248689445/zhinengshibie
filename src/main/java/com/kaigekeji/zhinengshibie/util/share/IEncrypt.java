package com.kaigekeji.zhinengshibie.util.share;

import java.util.Map;

/**
 * SHA,MD5加密接口 Created by 韦武良 on 2016年10月17日.
 */
public interface IEncrypt {
	/**
	 * 获取md5字符串
	 * 
	 * @param str
	 * @return
	 */
	public String encode16(String str);

	/**
	 * 获取md5字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public String encode16(byte[] bytes);

	/**
	 * 获取md5字符串
	 * 
	 * @param str
	 * @return
	 */
	public String encode32(String str);

	/**
	 * 获取md5字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public String encode32(byte[] bytes);

	/**
	 * 获取md5字符串
	 * 
	 * @param str
	 * @return
	 */
	public String encode64(String str);

	/**
	 * 获取md5字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public String encode64(byte[] bytes);

	/**
	 * 获取md5字符串
	 * 
	 * @param str
	 * @return
	 */
	public String encodeChar(String str);

	/**
	 * 获取md5字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public String encodeChar(byte[] bytes);

	/**
	 * base64解密
	 * 
	 * @param data
	 * @return
	 */
	public byte[] decode64(String data);

	/**
	 * base64解密
	 * 
	 * @param data
	 *            数据
	 * @param encode
	 *            编码格式
	 * @return
	 */
	public String decode64(String data, String encode);

	/**
	 * 数字签名方法（未加密）
	 * 
	 * @param param
	 *            url请求参数（空参数不参与签名）
	 * @return
	 */
	public String signature(Map<String, Object> param);

}
