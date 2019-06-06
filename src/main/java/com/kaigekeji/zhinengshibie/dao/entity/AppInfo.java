package com.kaigekeji.zhinengshibie.dao.entity;
/** 
 * 描述：应用程序信息  <p>
 */
public class AppInfo {

	private Integer serialkey; 	// ID
	private String appid;		// 应用ID
	private String secret; 		// 应用密匙
	private String token; 		// 微信公众号接口调用授权码
	private String sign;		// 小程序简写名称，格式：首拼音_xcx，如注册公司：zcgs_xcx
	private String templateid;	// 模板ID

	public Integer getSerialkey() {
		return serialkey;
	}

	public void setSerialkey(Integer serialkey) {
		this.serialkey = serialkey;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
	
}
