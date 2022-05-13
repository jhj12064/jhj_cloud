package com.jhj.utils.safe;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Jeremy
 */
@Slf4j
public class CryptAESUtil {
	
	/**
	 * 加密 
	 *  
	 * @param content 需要加密的内容 
	 * @param password  加密密码 
	 * @return 
	 */  
	private static byte[] encrypt(String content, String password) {  
	        try {             
	                KeyGenerator kgen = KeyGenerator.getInstance("AES");  
	                kgen.init(128, new SecureRandom(password.getBytes()));  
	                SecretKey secretKey = kgen.generateKey();  
	                byte[] enCodeFormat = secretKey.getEncoded();  
	                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
	                Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
	                byte[] byteContent = content.getBytes("utf-8");  
	                cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化   
	                byte[] result = cipher.doFinal(byteContent);  
	                return result; // 加密   
	        } catch (NoSuchAlgorithmException e) {  
	                e.printStackTrace();  
	        } catch (NoSuchPaddingException e) {  
	                e.printStackTrace();  
	        } catch (InvalidKeyException e) {  
	                e.printStackTrace();  
	        } catch (UnsupportedEncodingException e) {  
	                e.printStackTrace();  
	        } catch (IllegalBlockSizeException e) {  
	                e.printStackTrace();  
	        } catch (BadPaddingException e) {  
	                e.printStackTrace();  
	        }  
	        return null;  
	}  
	
	/**解密 
	 * @param content  待解密内容 
	 * @param password 解密密钥 
	 * @return 
	 */  
	private static byte[] decrypt(byte[] content, String password) {  
	        try {  
	                 KeyGenerator kgen = KeyGenerator.getInstance("AES");  
	                 kgen.init(128, new SecureRandom(password.getBytes()));  
	                 SecretKey secretKey = kgen.generateKey();  
	                 byte[] enCodeFormat = secretKey.getEncoded();  
	                 SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");              
	                 Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
	                cipher.init(Cipher.DECRYPT_MODE, key);// 初始化   
	                byte[] result = cipher.doFinal(content);  
	                return result; // 加密   
	        } catch (NoSuchAlgorithmException e) {  
	                e.printStackTrace();  
	        } catch (NoSuchPaddingException e) {  
	                e.printStackTrace();  
	        } catch (InvalidKeyException e) {  
	                e.printStackTrace();  
	        } catch (IllegalBlockSizeException e) {  
	                e.printStackTrace();  
	        } catch (BadPaddingException e) {  
	                e.printStackTrace();  
	        }  
	        return null;  
	}  
	
	
	/**将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */  
	private static String parseByte2HexStr(byte buf[]) {  
	        StringBuffer sb = new StringBuffer();  
	        for (int i = 0; i < buf.length; i++) {  
	                String hex = Integer.toHexString(buf[i] & 0xFF);  
	                if (hex.length() == 1) {  
	                        hex = '0' + hex;  
	                }  
	                sb.append(hex.toUpperCase());  
	        }  
	        return sb.toString();  
	}
	
	
	/**将16进制转换为二进制 
	 * @param hexStr 
	 * @return 
	 */  
	private static byte[] parseHexStr2Byte(String hexStr) {  
	        if (hexStr.length() < 1)  
	                return null;  
	        byte[] result = new byte[hexStr.length()/2];  
	        for (int i = 0;i< hexStr.length()/2; i++) {  
	                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
	                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
	                result[i] = (byte) (high * 16 + low);  
	        }  
	        return result;  
	} 
	
	
	/**
	 * 加密为String字符串
	 * @param content
	 * @param password
	 * @return
	 */
	public static String encrypt2String(String content, String password){
		log.debug("in content is:..........{}",content);
		byte[] encryptResult = encrypt(content, password);
    	String encryptResultStr = parseByte2HexStr(encryptResult); 
    	log.debug("after encrypt returnStr is:..........{}",encryptResultStr);
    	return encryptResultStr;
	}
	
	
	/**解密 
	 * @param password 解密密钥
	 * @return 
	 */  
	public static String decrypt2String(String encryptStr, String password) {
		//解密   
		log.debug("in encryptStr is:..........{}",encryptStr);
    	byte[] decryptFrom = parseHexStr2Byte(encryptStr);  
    	byte[] decryptResult = decrypt(decryptFrom,password);
    	String decryptResultStr  = new String(decryptResult);
    	log.debug("after decrypt content is:..........{}",decryptResultStr);
    	return decryptResultStr;
	}
	
	
	

    // 测试主函数  
    public static void main(String args[]) {  
    	String content = "12434+32";
    	String password = "wifizou-agt";  
    	//加密  
    	System.out.println("加密前：" + content);  
    	byte[] encryptResult = encrypt(content, password);  
    	String encryptResultStr = parseByte2HexStr(encryptResult);  
    	System.out.println("加密后：" + encryptResultStr);  
    	//解密   
    	byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);  
    	byte[] decryptResult = decrypt(decryptFrom,password);  
    	System.out.println("解密后：" + new String(decryptResult));
    }  
	
 
}