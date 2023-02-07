package br.caio303.users_auth_control.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

	public static String toSha256(String rawText) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] textInBytes = rawText.getBytes(StandardCharsets.UTF_8);
		byte[] encriptedText = md.digest(textInBytes);
		return new String(encriptedText, StandardCharsets.UTF_8);
	}
	
}