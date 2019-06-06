package com.kaigekeji.zhinengshibie.util.share;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * sha,md5加密类 Created by
 */
public class EncryptImpl implements IEncrypt {
	private String encoding; // 字符编码
	private MessageDigest md; // jdk加密类
	private BASE64Encoder encoder64 = new BASE64Encoder();
	private BASE64Decoder decoder64 = new BASE64Decoder();
	public final static String MD5 = "md5";
	public final static String SHA = "sha";

	/**
	 * 无参构造函数
	 */
	public EncryptImpl() {
		this(MD5, "utf-8");
	}

	/**
	 * 有参构造函数（加密类型不符则默认base加密）
	 * 
	 * @param algorithm
	 *            加密类型（sha,md5）
	 * @param encoding
	 *            字符编码
	 */
	public EncryptImpl(String algorithm, String encoding) {
		try {
			this.encoding = encoding;
			if (SHA.equalsIgnoreCase(algorithm) || MD5.equalsIgnoreCase(algorithm)) {
				md = MessageDigest.getInstance(algorithm);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String encode16(String str) {
		try {
			return encode16(str.getBytes(encoding));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String encode16(byte[] bytes) {
		return encode32(bytes).substring(8, 24);
	}

	@Override
	public String encode32(String str) {
		try {
			return encode32(str.getBytes(encoding));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String encode32(byte[] bytes) {
		try {
			bytes = digest(bytes);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(String.format("%02x", 0xFF & bytes[i]));
			}
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String encode64(String str) {
		try {
			return encode64(str.getBytes(encoding));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String encode64(byte[] bytes) {
		return encoder64.encode(digest(bytes));
	}

	@Override
	public String encodeChar(String str) {
		try {
			return encodeChar(str.getBytes(encoding));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String encodeChar(byte[] bytes) {
		bytes = digest(bytes);
		StringBuffer sb = new StringBuffer();
		int temp = 0;
		for (int i = 0; i < bytes.length; i++) {
			temp += (0xFF & bytes[i]);
			sb.append((char) (temp % 94 + 33));
			temp = temp / 94;
		}
		return sb.toString();
	}

	@Override
	public byte[] decode64(String data) {
		try {
			return decoder64.decodeBuffer(data);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String decode64(String data, String encode) {
		try {
			return new String(decode64(data), encode);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String signature(Map<String, Object> param) {
		List<String> paramNames = new ArrayList<String>(param.keySet());
		Collections.sort(paramNames);
		String paramValue = null;
		StringBuilder paramStr = new StringBuilder();
		for (String paramName : paramNames) {
			paramValue = String.valueOf(param.get(paramName));
			if (!(paramValue.trim().isEmpty() || "null".equals(paramValue))) {
				paramStr.append(paramValue);
			}
		}
		return paramStr.toString();
	}

	/**
	 * sha md5类型加密（类型不符则不加密）
	 * 
	 * @param bytes
	 *            加密前字节数据
	 * @return
	 */
	private byte[] digest(byte[] bytes) {
		if (md == null) {
			return bytes;
		}
		return md.digest(bytes);
	}

}
