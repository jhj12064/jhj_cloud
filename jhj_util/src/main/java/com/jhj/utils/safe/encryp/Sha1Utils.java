package com.jhj.utils.safe.encryp;

import java.security.MessageDigest;
import java.util.Formatter;

public class Sha1Utils {
	public static String encode(String source) {
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(source.getBytes("UTF-8"));
			return byteToHex(crypt.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

}
