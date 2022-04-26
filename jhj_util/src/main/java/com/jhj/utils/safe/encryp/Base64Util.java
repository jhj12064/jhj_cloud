package com.jhj.utils.safe.encryp;

import java.util.Base64;

public class Base64Util {

	@SuppressWarnings("restriction")
	public static String encode(byte[] s) {
		if (s == null)
			return null;
		final Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(s);
	}

	public static String encodeForUrl(byte[] s) {
		if (s == null)
			return null;
		String standerBase64 = encode(s);
		String encodeForUrl = standerBase64;
		// 转成针对url的base64编码
		encodeForUrl = encodeForUrl.replace("=", "");
		encodeForUrl = encodeForUrl.replace("+", "*");
		encodeForUrl = encodeForUrl.replace("/", "-");
		// 去除换行
		encodeForUrl = encodeForUrl.replace("\n", "");
		encodeForUrl = encodeForUrl.replace("\r", "");

		// 转换*号为 -x-
		// 防止有违反协议的字符
		encodeForUrl = encodeSpecialLetter1(encodeForUrl);

		return encodeForUrl;

	}

	/**
	 * 转换*号为 -x-， 为了防止有违反协议的字符，-x 转换为-xx
	 * 
	 * @param str
	 * @return
	 */
	private static String encodeSpecialLetter1(String str) {
		str = str.replace("-x", "-xx");
		str = str.replace("*", "-x-");
		return str;
	}

	/**
	 * 转换 -x-号为*，-xx转换为-x
	 * 
	 * @param str
	 * @return
	 */
	private static String decodeSpecialLetter1(String str) {
		str = str.replace("-x-", "*");
		str = str.replace("-xx", "-x");
		return str;
	}

	/**
	 * 将 s 进行 BASE64 编码
	 * 
	 * @param s
	 * @return
	 */
	public static String encode(String s) {

		if (s == null)
			return null;
		return encode(s.getBytes());
	}

	/**
	 * 将 BASE64 编码的字符串 s 进行解码
	 * 
	 * @param s
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static byte[] decode(String s) {
		if (s == null)
			return null;
		final Base64.Decoder decoder = Base64.getDecoder();
		try {
			byte[] b = decoder.decode(s);
			return b;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将 BASE64 编码的字符串 s 进行解码
	 * 
	 * @param s
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static byte[] decodeForUrl(String s) {
		if (s == null)
			return null;
		s = decodeSpecialLetter1(s);
		s = s.replace("*", "+");
		s = s.replace("-", "/");
		s += "=";
		final Base64.Decoder decoder = Base64.getDecoder();
		try {
			byte[] b = decoder.decode(s);
			return b;
		} catch (Exception e) {
			return null;
		}
	}


}
