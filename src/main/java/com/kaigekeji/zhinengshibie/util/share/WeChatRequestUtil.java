package com.kaigekeji.zhinengshibie.util.share;



import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.kaigekeji.zhinengshibie.util.JSONBeanUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.google.gson.Gson;
//import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.codec.binary.Base64;





/**
 * 微信接口调用工具类
 */
@SuppressWarnings("all")
public class WeChatRequestUtil {
	private static HttpRequestor http = new HttpRequestorImpl();

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	/**
	 * 获取微信小程序会话密钥
	 *
	 * @param appid
	 *            小程序应用ID
	 * @param secret
	 *            小程序应用密钥
	 * @param code
	 *            用户登录码
	 * @return 会话密钥
	 */
	public static Map getSessionKey(String appid, String secret, String code) {
		// 构建接口参数
		Map param = new HashMap();
		param.put("appid", appid);
		param.put("secret", secret);
		param.put("js_code", code);
		param.put("grant_type", "authorization_code");
		// 调用接口链接
		String result = http.doPost("https://api.weixin.qq.com/sns/jscode2session", param);
		return JSONBeanUtil.parseObject(result);
	}

	/**
	 * 获取微信小程序用户信息
	 *
	 * @param encryptedData
	 *            用户敏感信息加密数据
	 * @param session_key
	 *            会话密钥
	 * @param iv
	 *            加密算法的初始向量
	 * @return 用户信息
	 */
	public static Map getUserInfo(String session_key,String encryptedData, String iv) {
		// Base64解密
//		IEncrypt encrypt = new EncryptImpl();
		IEncrypt encrypt = new EncryptImpl();
		byte[] encryptedDatas = encrypt.decode64(encryptedData);
		byte[] session_keys = encrypt.decode64(session_key);
		byte[] ivs = encrypt.decode64(iv);

		try {
			//秘钥不足16位时
			int base=16;
			if(session_keys.length % base!=0) {
				int groups = session_keys.length / base + (session_keys.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(session_keys, 0, temp, 0, session_keys.length);
				session_keys = temp;
			}
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());// 添加PKCS7Padding支持
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");

			SecretKeySpec spec = new SecretKeySpec(session_keys, "AES");
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
			parameters.init(new IvParameterSpec(ivs));
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化

			// 执行敏感数据解密
			byte[] resultByte = cipher.doFinal(encryptedDatas);

			if (resultByte != null && resultByte.length > 0) {
				String result = new String(resultByte);
				return JSONBeanUtil.parseObject(result);
			}
			return new HashMap();

		} catch (Exception e) {
			System.out.println("哎哎哎！！");
			throw new RuntimeException("encryptedData:" + encryptedData + ",session_key:" + session_key + ",iv:" + iv, e);
		}
	}

	/**
	 * 获取微信小程序或公众号接口调用凭证
	 *
	 * @param appid
	 *            公众号应用ID
	 * @param secret
	 *            公众号应用密钥
	 * @return 接口调用凭证
	 */
	public static Map getAccessToken(String appid, String secret) {
		// 构建接口参数
		Map param = new HashMap();
		param.put("grant_type", "client_credential");
		param.put("appid", appid);
		param.put("secret", secret);

		// 调用接口链接
		String result = http.doPost("https://api.weixin.qq.com/cgi-bin/token", param);
		return JSONBeanUtil.parseObject(result);
	}

	/**
	 * 获取微信公众号用户授权码
	 *
	 * @param appid
	 *            公众号应用ID
	 * @param redirect_uri
	 *            重定向uri
	 * @param scope
	 *            获取用户信息(true)/不获取用户信息(false)
	 * @return 用户授权码
	 */
	public static String getCode(String appid, String redirect_uri, boolean scope) {
		// 构建接口参数
		Map param = new HashMap();
		param.put("appid", appid);
		param.put("redirect_uri", redirect_uri);
		param.put("response_type", "code");
		param.put("scope", scope ? "snsapi_userinfo" : "snsapi_base");
		param.put("state", "STATE#wechat_redirect");

		// 调用接口链接
		String result = http.doPost("https://open.weixin.qq.com/connect/oauth2/authorize", param);
		return result;
	}

	/**
	 * 获取微信公众号用户授权凭证
	 *
	 * @param appid
	 *            公众号应用ID
	 * @param secret
	 *            公众号应用密钥
	 * @param code
	 *            用户授权码
	 * @return 用户授权凭证
	 */
	public static Map getAccessToken(String appid, String secret, String code) {
		// 构建接口参数
		Map param = new HashMap();
		param.put("appid", appid);
		param.put("secret", secret);
		param.put("code", code);
		param.put("grant_type", "authorization_code");

		// 调用接口链接
		String result = http.doPost("https://api.weixin.qq.com/sns/oauth2/access_token", param);
		return JSONBeanUtil.parseObject(result);
	}

	/**
	 * 获取微信公众号刷新后用户授权凭证
	 *
	 * @param appid
	 *            公众号应用ID
	 * @param refresh_token
	 *            刷新后用户授权凭证
	 * @return 用户授权凭证
	 */
	public static Map getRefreshToken(String appid, String refresh_token) {
		// 构建接口参数
		Map param = new HashMap();
		param.put("appid", appid);
		param.put("grant_type", "refresh_token");
		param.put("refresh_token", refresh_token);

		// 调用接口链接
		String result = http.doPost("https://api.weixin.qq.com/sns/oauth2/access_token", param);
		return JSONBeanUtil.parseObject(result);
	}

	/**
	 * 获取微信公众号用户信息
	 *
	 * @param access_token
	 *            用户授权凭证
	 * @param openid
	 *            用户openid
	 * @param lang
	 *            语言类型 (zh_CN 简体,zh_TW 繁体,en 英语)
	 * @return
	 */
	public static Map getUserInfos(String access_token, String openid, String lang) {
		// 构建接口参数
		Map param = new HashMap();
		param.put("access_token", access_token);
		param.put("openid", openid);
		param.put("lang", lang);

		// 调用接口链接
		String result = http.doPost("https://api.weixin.qq.com/sns/userinfo", param);
		return JSONBeanUtil.parseObject(result);
	}

	/**
	 * 获取微信带参数的二维码
	 *
	 * @param action_token
	 *            公众号接口调用凭证
	 * @param expire_seconds
	 *            有效时间 （-1 ~ 2592000 秒）
	 * @param scene_id
	 *            场景值ID
	 */
	public static Map getWeChatQRCode(String action_token, int expire_seconds, String scene_id) {
		// 拼接二维码请求参数
		Map param = new HashMap();
		Map action_info = new HashMap();
		Map scene = new HashMap();
		if (expire_seconds > 0) {
			param.put("expire_seconds", expire_seconds);
			param.put("action_name", "QR_LIMIT_SCENE");
			scene.put("scene_id", scene_id);
		} else {
			param.put("action_name", "QR_LIMIT_STR_SCENE");
			scene.put("scene_str", scene_id);
		}
		action_info.put("scene", scene);
		param.put("action_info", action_info);

		// 调用接口链接
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + action_token;
		String result = http.doPost(url, JSONBeanUtil.toJSONString(param));
		return JSONBeanUtil.parseObject(result);
	}

	/**
	 * 发送小程序模板消息
	 * @param template_id {@link String} 模板ID
	 * @param page {@link }
	 * @param openid
	 * @param prepay_id
	 * @param data
	 * @param access_token
	 * @return
	 */
	public static Map<String, Object> sendMessage(String template_id, String page, String openid, String prepay_id,
												  String data, String access_token) {
		// 构建接口参数
		Map param = new HashMap();
		param.put("touser", openid);
		param.put("template_id", template_id);
		param.put("page", page);
		param.put("form_id", prepay_id);
		param.put("data", JSONBeanUtil.parseObject(data));

		// 调用接口链接
		String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + access_token;
		String result = http.doPost(url, JSONBeanUtil.toJSONString(param), "text/json");
		return JSONBeanUtil.parseObject(result);
	}

	/**
	 * 发送公众号模板消息
	 */
	public static Map<String, Object> sendMessage(String template_id, String url, String openid, String data, String access_token) {
		// 构建接口参数
		Map param = new HashMap();
		param.put("touser", openid);
		param.put("template_id", template_id);
		param.put("url", url);
		param.put("topcolor", "#00FF00");
		param.put("data", JSONBeanUtil.parseObject(data));

		// 调用接口链接
		url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
		String result = http.doPost(url, JSONBeanUtil.toJSONString(param), "text/json");
		return JSONBeanUtil.parseObject(result);
	}

	public static Map<String, Object> newsendMessage(String openid, String template_id ,String fromId, String data, String access_token) {
		// 构建接口参数
		// \"touser\": \"ooH8JjyugPoLwL9s6X6dv2HI-O6Q\", 
		Map param = new HashMap();
		param.put("touser", openid);
		param.put("template_id", template_id);
		param.put("color", "#00FF00");
		param.put("form_id", fromId);
		param.put("data", JSONBeanUtil.parseObject(data));
		String json = new Gson().toJson(param);
		String jsonString = JSONBeanUtil.toJSONString(param);
		// 调用接口链接
		String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + access_token;
		String result = http.doPost(url, jsonString, "text/json");
		return JSONBeanUtil.parseObject(result);
	}

}
