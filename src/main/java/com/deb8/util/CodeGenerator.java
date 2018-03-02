package com.deb8.util;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class CodeGenerator {
	private static final String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int CODE_LENGTH = 10;

	public static String generateCode() {
		SecureRandom secureRandom = new SecureRandom();

		String code = "";
		int length = CODE_LENGTH;

		while (length-- > 0) {
			code += base.charAt(secureRandom.nextInt(base.length()));
		}

		return code;
	}
}