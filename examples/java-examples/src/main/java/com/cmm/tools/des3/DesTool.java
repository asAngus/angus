package com.cmm.tools.des3;


import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

/**
 * ����/���빤����
 * 
 */
public class DesTool {
	private static Inner inner = Inner.getInstance();
	private static Logger logger=Logger.getLogger("Deshandler");
	/**
	 * 3DES�����ֽ�����
	 * @param b �����ܵ��ֽ�����
	 * @return 3DES���ܺ�Ľڽ�����
	 */
	public synchronized static byte[] decrypt(byte[] b) {
		try {
			return inner.decrypt(b);
		} catch (Exception ex) {
			logger.error("3DES���ܳ���,", ex);
			return null;
		}
	}

	/**
	 * �����ַ���
	 * @param strIn ����ܵ��ַ���
	 * @return ���ܺ���ַ���,�쳣���ؿ�
	 */
	public synchronized static String decrypt(String strIn){
		try {
			return new String(decrypt(HexUtils.fromHexString(strIn)));
		} catch (Exception ex) {
			logger.error("3DES���ܳ���,", ex);
			return null;
		}
	}
	
	/**
	 * �����ֽ�����
	 * @param arrB ����ܵ��ֽ�����
	 * @return ���ܺ���ֽ�����
	 */
	public synchronized static byte[] encrypt(byte[] arrB){
		try {
			return inner.decrypt(arrB);
		} catch (Exception ex) {
			logger.error("3DES���ܳ���,", ex);
			return null;
		}
	}
	/**
	 * �����ַ���
	 * @param str �����ܵ��ַ���
	 * @return 3DES���ܺ���ַ���
	 */
	public synchronized static String encrypt(String str) {
		try {
			return inner.encrypt(str);
		} catch (Exception ex) {
			logger.error("3DES���ܳ���,", ex);
			return null;
		}
	}
	
	/**
	 * ��ȡ��ǰkeyֵ
	 */
	public static String getKeyStr() {
		return inner.getKeyStr();
	}
	/**
	 * ��̬�޸�keyֵ
	 * @param keyStr
	 */
	public synchronized static void setKeyStr(String keyStr) {
		inner.setKeyStr(keyStr);
	}
	/**
	 * ��ȡ��ǰ��������
	 * @return
	 */
	public static String getDesStr() {
		return inner.getDesStr();
	}

	/**
	 * ��̬�޸ļ�������
	 * @param desStr
	 */
	public static void setDesStr(String desStr) {
		inner.setDesStr(desStr);
	}

	/**
	 * ��ȡ��ǰ���ܷ�ʽ
	 * @return
	 */
	public static String getAlgorithm() {
		return inner.getAlgorithm();
	}
	/**
	 * ��̬�޸ļ��ܷ�ʽ/��䷽ʽ
	 * @param algorithm
	 */
	public synchronized static void setAlgorithm(String algorithm) {
		inner.setAlgorithm(algorithm);
	}

	/**
	 * ��ָ���ַ���������Կ
	 * @param arrBTmp ���ɸ��ַ������ֽ�����
	 * @return ���ɵ���Կ
	 */
	public static byte[] HexStringToByteArray(String s) {
		byte[] buf = new byte[s.length() / 2];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = (byte) (chr2hex(s.substring(i * 2, i * 2 + 1)) * 0x10 + chr2hex(s
					.substring(i * 2 + 1, i * 2 + 2)));
		}
		return buf;
	}

	private static byte chr2hex(String chr) {
		if (chr.equals("0")) {
			return 0x00;
		} else if (chr.equals("1")) {
			return 0x01;
		} else if (chr.equals("2")) {
			return 0x02;
		} else if (chr.equals("3")) {
			return 0x03;
		} else if (chr.equals("4")) {
			return 0x04;
		} else if (chr.equals("5")) {
			return 0x05;
		} else if (chr.equals("6")) {
			return 0x06;
		} else if (chr.equals("7")) {
			return 0x07;
		} else if (chr.equals("8")) {
			return 0x08;
		} else if (chr.equals("9")) {
			return 0x09;
		} else if (chr.equals("A")) {
			return 0x0a;
		} else if (chr.equals("B")) {
			return 0x0b;
		} else if (chr.equals("C")) {
			return 0x0c;
		} else if (chr.equals("D")) {
			return 0x0d;
		} else if (chr.equals("E")) {
			return 0x0e;
		} else if (chr.equals("F")) {
			return 0x0f;
		}
		return 0x00;
	}

	public static void main(String args[]) throws Exception {

	}
}





/**
 * �ڲ���,��Ϊ������ĵ���,�Ա�ͨ���ϲ����key,���������Ķ�̬�޸�
 * 
 * @author lydawen 2009-12-3
 * 
 */
final class Inner {
	private String keyStr = "123456789012345678901234";
	private String desStr = "12345678";
	private String algorithm = "DESede/CBC/PKCS7Padding";

	private static Inner instance;
	private Key key = getKey(keyStr);

	private byte[] desInitValue = HexUtils.strToByte(desStr);// Ĭ������
	private IvParameterSpec ivspec = new IvParameterSpec(desInitValue);

	private Cipher encryptCipher = null;

	private Cipher decryptCipher = null;

	/**
	 * ���ι��췽��
	 */
	private Inner() {

	}

	/**
	 * ��ȡʵ��
	 * 
	 * @return
	 */
	static Inner getInstance() {
		if (instance == null) {
			synchronized(Inner.class){
				if(instance == null){
						Security.addProvider(new com.sun.crypto.provider.SunJCE());
						Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());//���PKCS7Padding֧��
						instance = new Inner();
				}
		}
		}
		return instance;
	}

	/**
	 * �����ֽ�����
	 * @param arrB ����ܵ��ֽ�����
	 * @return ���ܺ���ֽ�����
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] arrB) throws Exception {
		encryptCipher = Cipher.getInstance(algorithm);
		encryptCipher.init(Cipher.ENCRYPT_MODE, key, ivspec);
		return encryptCipher.doFinal(arrB);
	}

	/**
	 * �����ַ���
	 * @param strIn ����ܵ��ַ���
	 * @return ���ܺ���ַ���
	 * @throws Exception
	 */
	public String encrypt(String strIn) throws Exception {
		encryptCipher = Cipher.getInstance(algorithm);
		encryptCipher.init(Cipher.ENCRYPT_MODE, key, ivspec);
		return HexUtils.toHexString(encrypt(strIn.getBytes()));
	}

	/**
	 * �����ֽ�����
	 * @param arrB ����ܵ��ֽ�����
	 * @return ���ܺ���ֽ�����
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] arrB) throws Exception {
		decryptCipher = Cipher.getInstance(algorithm);
		decryptCipher.init(Cipher.DECRYPT_MODE, key, ivspec);
		return decryptCipher.doFinal(arrB);
	}

	/**
	 * �����ַ���
	 * @param strIn ����ܵ��ַ���
	 * @return ���ܺ���ַ���
	 */
	public String decrypt(String strIn) throws Exception {
		return HexUtils.toHexString(decrypt(HexUtils.fromHexString(strIn)));
	}

	private Key getKey(String str) {
		Key key = new SecretKeySpec(str.getBytes(), algorithm);
		return key;
	}


	private byte chr2hex(String chr) {
		if (chr.equals("0")) {
			return 0x00;
		} else if (chr.equals("1")) {
			return 0x01;
		} else if (chr.equals("2")) {
			return 0x02;
		} else if (chr.equals("3")) {
			return 0x03;
		} else if (chr.equals("4")) {
			return 0x04;
		} else if (chr.equals("5")) {
			return 0x05;
		} else if (chr.equals("6")) {
			return 0x06;
		} else if (chr.equals("7")) {
			return 0x07;
		} else if (chr.equals("8")) {
			return 0x08;
		} else if (chr.equals("9")) {
			return 0x09;
		} else if (chr.equals("A")) {
			return 0x0a;
		} else if (chr.equals("B")) {
			return 0x0b;
		} else if (chr.equals("C")) {
			return 0x0c;
		} else if (chr.equals("D")) {
			return 0x0d;
		} else if (chr.equals("E")) {
			return 0x0e;
		} else if (chr.equals("F")) {
			return 0x0f;
		}
		return 0x00;
	}

	public String getKeyStr() {
		return keyStr;
	}

	/**
	 * ��̬�޸�key��ʼֵ
	 * @param keyStr
	 */
	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
		this.key=getKey(keyStr);
	}

	public String getDesStr() {
		return desStr;
	}

	/**
	 * ��̬�޸ļ�������
	 * @param desStr
	 */
	public void setDesStr(String desStr) {
		this.desStr = desStr;
		this.desInitValue = HexUtils.strToByte(this.desStr);
		this.ivspec = new IvParameterSpec(this.desInitValue);
	}

	public String getAlgorithm() {
		return algorithm;
	}
	/**
	 * ��̬�޸ļ��ܷ�ʽ/��䷽ʽ
	 * @param algorithm
	 */
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
		this.key=getKey(this.keyStr);
	}

}
