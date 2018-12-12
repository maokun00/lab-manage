package com.lab.manage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

public class CipherAESUtil {
	public static String CIPHER_ALGORITHM = "AES";
	private static Logger logger = LoggerFactory.getLogger(CipherAESUtil.class);
	private final static String KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmEi8ZFDI3UIKef1J6M6fUcgealECtCZioRqUw9Yko296r0x6DGNSyKBGdXQE7XVEwmdyY3IjvK5eodII6VlF";

	public static Key getKey(String strKey) {
		Key retVal = null;
		try {
			if (strKey == null) {
				strKey = "";
			}
			KeyGenerator generator = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(strKey.getBytes());
			generator.init(128, secureRandom);
			retVal = generator.generateKey();
		} catch (Exception e) {
			logger.error("[生成密钥异常]",e);
		}
		return  retVal;
	}

	/**
	 * AES加密
	 * @param plainText 明文
	 * @return
	 */
	public static String encrypt(String plainText) {
		String retVal = null;
		try{
			SecureRandom sr = new SecureRandom();
			Key secureKey = getKey(KEY);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secureKey, sr);
			byte[] bt = cipher.doFinal(plainText.getBytes());
			retVal = new BASE64Encoder().encode(bt);
		}catch (Exception e) {
			logger.error("[AES加密异常]",e);
		}
		return retVal;

	}
	/**
	 * AES解密
	 * @param cipherText 密文
	 * @return
	 */
	public static String decrypt(String cipherText)  {
		String retVal = null;
		try {
			SecureRandom sr = new SecureRandom();
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			Key secureKey = getKey(KEY);
			cipher.init(Cipher.DECRYPT_MODE, secureKey, sr);
			byte[] res = new BASE64Decoder().decodeBuffer(cipherText);
			byte[] bytes = cipher.doFinal(res);
			retVal =  new String(bytes);
		}catch (Exception e) {
			logger.error("[AES解密异常]",e);
		}
		return retVal;
	}

	/**将二进制转换成16进制
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i]  );
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
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1){
			return null;
		}
		byte[] result = new byte[hexStr.length()/2];
		for (int i = 0;i< hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
