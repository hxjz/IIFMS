package com.iif.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 服务系统MD5加密方法
 * @author LiuM
 *
 */
public class Md5 {
	public static String md5s(String plainText) {
		String str = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			
			int i;
			
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0){
					i += 256;
				}
				if (i < 16){
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return str;
	}
	public static void main(String[] args) {
		System.out.println(Md5.md5s("123456"));
	}
}
