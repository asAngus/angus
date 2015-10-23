/**
 * 
 */
package com.angus.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/*******************************************************************************
 * AES加解密算法是一种对称的加解密算法 加密时先对源字符串进行AES-128-CBC加密，然后再对加密后的字符串做Base64加密
 * 解密则是先对源字符串进行Base64解密，然后再进行AES解密
 * 
 * 使用AES-128-CBC加密模式，key需要为16位。iv也必须为16位
 * 
 * 默认采用utf-8对加密内容进行编码
 *
 * 为和PHP默认对齐方式保持一致，加密内容采用不足16位倍数末尾补\0的方式来对齐
 *
 * @author colong
 * 
 */
public class AesUtil {

	private static final String DEFAULT_IV = "#}.lJP44O,jQGVn%";

	public static String decrypt(String source, String originKey) throws Exception {
		return decrypt(source, originKey, DEFAULT_IV);
	}

	public static String decrypt(String source, String originKey, String iv) throws Exception {
		String key = originKey;
		if (originKey == null) {
			throw new Exception("Key为空null");
		}
		// 判断Key是否为16位
		if (key.length() > 16) {
			key = key.substring(0, 16);
		} else if (key.length() < 16) {
			key = Hex.encodeHexString(DigestUtils.md5(key)).substring(0, 16);
		}

		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");// "算法/模式/补码方式"
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");

		// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

		// 先用base64解密
		byte[] base64DecryptStr = Base64.getDecoder().decode(source);

		byte[] outText = cipher.doFinal(base64DecryptStr);

		// trim去掉最后补齐的\0
		return new String(outText, "utf-8").trim();
	}

	public static String encrypt(String source, String originKey) throws Exception {
		return encrypt(source, originKey, DEFAULT_IV);
	}

	public static String encrypt(String source, String originKey, String iv) throws Exception {
		String key = originKey;
		if (originKey == null) {
			throw new Exception("Key为空null");
		}
		// 判断Key是否为16位
		if (key.length() > 16) {
			key = key.substring(0, 16);
		} else if (key.length() < 16) {
			key = Hex.encodeHexString(DigestUtils.md5(key)).substring(0, 16);
		}

		// Cipher cipher =
		// Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");// "算法/模式/不自动补齐"
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
		// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

		// 因为采用NoPadding方式，需要手动补齐源数据byte数为16的整数倍，为和PHP默认补齐方式保持一致，采用\0补齐
		byte[] srcByteArray = source.getBytes("utf-8");

		// 计算需要补\0的位数
		int n = 16 - (source.getBytes("utf-8").length % 16);

		// 如果原始数据就是对齐的，则不再补齐
		if (n == 16) {
			n = 0;
		}

		byte[] byteFillZero = new byte[srcByteArray.length + n];

		System.arraycopy(srcByteArray, 0, byteFillZero, 0, srcByteArray.length);

		for (int i = srcByteArray.length; i < byteFillZero.length; i++) {
			byteFillZero[i] = (byte) '\0';
		}

		byte[] outText = cipher.doFinal(byteFillZero);

		// 此处使用BASE64做转码功能，同时能起到二次加密的作用。
		return Base64.getEncoder().encodeToString(outText);
	}

	public static String doAes(String Aes) {

		String key = "#}.lJP44O,jQGVn%";
		String iv = "1234567890123456";
		try {
			Aes = encrypt(Aes, key, iv).replace("\n", "").replace("\r", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Aes;
	}

	public static void main(String[] args) {
		/*
		 * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符（虽然不会报错）
		 * 使用AES-128-CBC加密模式，key需要为16位。iv也必须为16位。
		 */
		String key = "#}.lJP44O,jQGVn%";
		// String key = "bbb";
		String iv = "1234567890123456";

		try {
			String encryptStr = encrypt("xgsdk测试串", key, iv);
			System.out.println("Encrypted Str: " + encryptStr);

			encryptStr = encryptStr.replace("\n", "");
			encryptStr = encryptStr.replace("\r", "");
			System.out.println("Encrypted Str: " + encryptStr);

			// "Q6uairk4CiO26nIHpWYQRQ=="
			String decryptStr = decrypt(encryptStr, key, iv);
			System.out.println("Decrypted Str: " + decryptStr);
			System.out.println(Hex.encodeHexString(DigestUtils.md5("aaa")));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
