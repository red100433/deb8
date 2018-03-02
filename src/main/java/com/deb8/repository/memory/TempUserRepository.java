package com.deb8.repository.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.deb8.exception.ExceptionMessage;
import com.deb8.exception.InvalidRequestException;
import com.deb8.model.TempUser;

/**
 * 이메일 인증을 받지 않은 유저를 보관 및 처리하기 위한 핸들러
 * EXPIRE_TIME 만큼 저장하고 있다가 요청이 들어오지 않으면 폐기한다
 * 
 * 각 유저마다 고유한 key를 할당해서 이메일 인증시 key값으로 유저를 판단한다
 * 
 * @author sunyoung.choi
 *
 */

public class TempUserRepository {
	private static TempUserRepository INSTANCE;
	private static Map<String, TempUser> tempUserMap;
	private static final int EXPIRE_TIME = 1000 * 60 * 60;
	private static final int PASSWD_MIN_LENGTH = 5;
	private static final int PASSWD_MAX_LENGTH = 15;

	private TempUserRepository() {}

	public static TempUserRepository getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TempUserRepository();
			tempUserMap = new HashMap<>();
		}
		return INSTANCE;
	}

	public String setTempUser(TempUser tempUser) throws InvalidRequestException {
		String password = tempUser.getPasswd();

		boolean isTooShort = password.length() < PASSWD_MIN_LENGTH;
		boolean isTooLong = password.length() > PASSWD_MAX_LENGTH;

		if (isTooShort || isTooLong) {
			throw new InvalidRequestException(ExceptionMessage.INVALID_PASSWD_LENGTH);
		}

		long now = System.currentTimeMillis();
		tempUser.setExpiredAt(now + EXPIRE_TIME);

		String key = generateKey(tempUser);
		tempUserMap.put(key, tempUser);

		return key;
	}
	
	public boolean has(String key) {
		return tempUserMap.containsKey(key);
	}

	public TempUser getTempUserByKey(String key) {
		removeExpiredUser();
		return tempUserMap.remove(key);
	}

	private String generateKey(TempUser tempUser) {
		String mailSeed = tempUser.getEmail();
		String passwdSeed = tempUser.getPasswd();
		long timeSeed = tempUser.getExpiredAt();

		int base = Objects.hash(mailSeed, passwdSeed, timeSeed);
		return String.valueOf(Math.abs(base));
	}

	private void removeExpiredUser() {
		long now = System.currentTimeMillis();

		for (Map.Entry<String, TempUser> entry : tempUserMap.entrySet()) {
			long expiredAt = entry.getValue().getExpiredAt();

			if (expiredAt <= now) {
				tempUserMap.remove(entry.getKey());
			}
		}
	}
}
