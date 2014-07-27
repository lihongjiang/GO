package com.netfuture.go.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {
	public static String generateMD5Key(String fileName){
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] digestedBytes = digest.digest(fileName.getBytes());
			return new String(digestedBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}	
		return null;
	}
}
