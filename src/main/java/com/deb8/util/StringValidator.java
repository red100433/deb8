package com.deb8.util;

import com.deb8.exception.ExceptionMessage;
import com.deb8.exception.InvalidRequestException;

public class StringValidator {
	private static final int TITLE_MAX = 50;
	private static final int CONTENTS_MAX = 200;
	private static final int PASSWORD_MAX = 15;
	private static final int PASSWORD_MIN = 5;
	private static final int KEYWORD_MAX = 100;
	private static final int BIO_MAX = 100;

	public static void checkTitle(String title) throws InvalidRequestException {
		checkMaxAndEmpty(TITLE_MAX, title);
	}

	public static void checkKeyword(String keyword) throws InvalidRequestException {
		checkMaxAndEmpty(KEYWORD_MAX, keyword);
	}

	public static void checkContents(String contents) throws InvalidRequestException {
		checkMaxAndEmpty(CONTENTS_MAX, contents);
	}

	public static void checkBio(String bio) throws InvalidRequestException {
		checkMaxAndEmpty(BIO_MAX, bio);
	}

	public static void checkPassword(String password) throws InvalidRequestException {
		if (password.length() < PASSWORD_MIN || password.length() > PASSWORD_MAX) {
			throw new InvalidRequestException(ExceptionMessage.INVALID_PASSWD_LENGTH);
		}
	}

	private static void checkMaxAndEmpty(int max, String target) throws InvalidRequestException {
		if (target.length() == 0) {
			throw new InvalidRequestException(ExceptionMessage.EMPTY_CONTENTS);
		}

		if (target.length() > max) {
			throw new InvalidRequestException(ExceptionMessage.EXCEED_LIMIT);
		}
	}
}