package com.kaigekeji.zhinengshibie.util.share;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.security.KeyStore;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * http请求接口 Created by 韦武良 on 2016年11月26日.
 */
@SuppressWarnings("all")
public interface HttpRequestor {
	/**
	 * 提交get请求
	 * 
	 * @param url
	 *            请求url
	 * @return
	 */
	public String doGet(String url);

	/**
	 * 提交get请求
	 * 
	 * @param url
	 *            请求url
	 * @param param
	 *            请求参数
	 * @return
	 */
	public String doGet(String url, String param);

	/**
	 * 提交get请求
	 * 
	 * @param url
	 *            请求url
	 * @param param
	 *            请求参数
	 * @return
	 */
	public String doGet(String url, Map param);

	/**
	 * 提交post请求
	 * 
	 * @param url
	 *            请求url
	 * @param param
	 *            请求参数
	 * @return
	 */
	public String doPost(String url, String param);

	/**
	 * 提交post请求
	 * 
	 * @param url
	 *            请求url
	 * @param param
	 *            请求参数
	 * @param content_Type
	 *            请求头类型
	 * @return
	 */
	public String doPost(String url, String param, String content_Type);

	/**
	 * 提交post请求
	 * 
	 * @param url
	 *            请求url
	 * @param param
	 *            请求参数
	 * @return
	 */
	public String doPost(String url, Map param);

	/**
	 * 提交post请求
	 * 
	 * @param url
	 *            请求url
	 * @param param
	 *            请求参数
	 * @param content_Type
	 *            请求头类型
	 * @return
	 */
	public String doPost(String url, Map param, String content_Type);

	/**
	 * 参数排序
	 * 
	 * @param param
	 *            url请求参数
	 * @return
	 */
	public String sort(Map param);

	/**
	 * 发送http请求
	 * 
	 * @param url
	 *            请求路径
	 * @param param
	 *            请求参数
	 * @param request_Type
	 *            请求类型
	 * @param content_Type
	 *            请求头类型
	 * @return
	 */
	public InputStream request(HttpURLConnection conn, String param, String request_Type, String content_Type);

	/**
	 * 打开http连接
	 * 
	 * @param url
	 *            网址
	 * @return 连接对象
	 */
	public HttpURLConnection open(String url);

	/**
	 * 打开https连接
	 * 
	 * @param url
	 *            网址
	 * 
	 * @return 连接对象
	 */
	public HttpsURLConnection open(String url, String path, String secret, TrustManager[] tms);

	/**
	 * 获取证书数据对象
	 * 
	 * @param path
	 *            证书路径
	 * @param secret
	 *            证书密钥
	 * @return
	 */
	public KeyStore getKeyStore(String path, String secret);

	/**
	 * 获取调用证书管理器
	 * 
	 * @param path
	 *            证书路径
	 * @param secret
	 *            证书密钥
	 * @return
	 */
	public KeyManager[] getKeyManager(String path, String secret);

	/**
	 * 获取域名证书管理器
	 * 
	 * @param path
	 *            证书路径
	 * @param secret
	 *            证书密钥
	 * @return
	 */
	public TrustManager[] getTrustManagers(String path, String secret);

	/**
	 * 获取ssl证书socket工厂
	 * 
	 * @param path
	 *            调用证书路径
	 * @param secret
	 *            调用证书密钥
	 * @param tms
	 *            域名证书管理器
	 * @return
	 */
	public SSLSocketFactory getSocketFactory(String path, String secret, TrustManager[] tms);
}