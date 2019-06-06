package com.kaigekeji.zhinengshibie.util.share;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;




/**
 * http请求类 Created by 韦武良 on 2016年10月17日.
 */
@SuppressWarnings("all")
public class HttpRequestorImpl implements HttpRequestor {
	private Proxy proxy = null;
	private String encoding = "UTF-8"; // 默认字符编码
	private final static Map<String, String> MAP = new HashMap<String, String>();

	static {
		MAP.put("p12", "PKCS12");
		MAP.put("jks", "JKS");
	}

	public HttpRequestorImpl() {
	}

	/**
	 * 有参构造函数
	 *
	 * @param charset
	 *            字符编码
	 * @param proxyHost
	 *            代理主机
	 * @param proxyPort
	 *            代理端口
	 */
	public HttpRequestorImpl(String encoding, String proxyHost, Integer proxyPort) {
		this.encoding = encoding;
		this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
	}

	@Override
	public String doGet(String url) {
		return doGet(url, "");
	}

	@Override
	public String doGet(String url, String param) {
		IStream stream = getStream(encoding);
		if (param != null && !param.trim().isEmpty()) {
			url = url + "?" + param;
		}
		String result = stream.reader(request(open(url), param, "GET", null));
		stream.closeInput();
		return result;
	}

	@Override
	public String doGet(String url, Map param) {
		return doGet(url, sort(param));
	}

	@Override
	public String doPost(String url, String param) {
		return doPost(url, param, null);
	}

	@Override
	public String doPost(String url, String param, String content_Type) {
		IStream stream = getStream(encoding);
		String result = stream.reader(request(open(url), param, "POST", content_Type));
		stream.closeInput();
		return result;
	}

	@Override
	public String doPost(String url, Map param) {
		return doPost(url, param, null);
	}

	@Override
	public String doPost(String url, Map param, String content_Type) {
		return doPost(url, sort(param), content_Type);
	}

	@Override
	public String sort(Map param) {
		try {
			List<String> list = new ArrayList<String>(param.keySet());
			Collections.sort(list);
			StringBuilder sb = new StringBuilder();
			String value = null;
			for (String key : list) {
				if (param.get(key) == null) {
					continue;
				}
				value = param.get(key).toString();
				if (!value.trim().isEmpty()) {
					value = URLEncoder.encode(URLDecoder.decode(value, encoding), encoding);
					sb.append("&").append(key).append("=").append(value);
				}
			}
			return sb.length() == 0 ? "" : sb.substring(1);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public InputStream request(HttpURLConnection conn, String param, String request_Type, String content_Type) {
		IStream stream = getStream(encoding);
		try {
			request_Type = request_Type.toUpperCase();
			conn.setRequestMethod(request_Type);
			if (content_Type != null) {
				conn.setRequestProperty("Content-Type", content_Type);
			}
			if ("POST".equals(request_Type)) {
				conn.setDoOutput(true);
				stream.writer(conn.getOutputStream(), param);
			}
			if (conn.getResponseCode() != 200) {
				throw new Exception("发送" + request_Type + "请求失败,返回状态码:" + conn.getResponseCode());
			}
			return conn.getInputStream();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			stream.closeOutput();
		}
	}

	@Override
	public HttpURLConnection open(String url) {
		try {
			URL httpUrl = new URL(url);
			URLConnection conn = null;
			if (proxy != null) {
				conn = httpUrl.openConnection(proxy);
			} else {
				conn = httpUrl.openConnection();
			}
			return (HttpURLConnection) conn;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public HttpsURLConnection open(String url, String path, String secret, TrustManager[] tms) {
		try {
			HttpsURLConnection conn = (HttpsURLConnection) open(url);
			if (path != null && secret != null) {
				conn.setSSLSocketFactory(getSocketFactory(path, secret, tms));
			}
			return conn;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public KeyStore getKeyStore(String path, String secret) {
		try {
			String suffix = path.substring(path.lastIndexOf('.') + 1);
			KeyStore store = KeyStore.getInstance(MAP.get(suffix));
			store.load(new FileInputStream(path), secret.toCharArray());
			return store;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public KeyManager[] getKeyManager(String path, String secret) {
		try {
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(getKeyStore(path, secret), secret.toCharArray());
			return kmf.getKeyManagers();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public TrustManager[] getTrustManagers(String path, String secret) {
		try {
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(getKeyStore(path, secret));
			return tmf.getTrustManagers();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public SSLSocketFactory getSocketFactory(String path, String secret, TrustManager[] tms) {
		try {
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(getKeyManager(path, secret), tms, new SecureRandom());
			return sslContext.getSocketFactory();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取流操作对象
	 *
	 * @param encoding
	 *            字符编码
	 * @return
	 */
	private IStream getStream(String encoding) {
		return new StreamImpl(encoding.toUpperCase());
	}

}