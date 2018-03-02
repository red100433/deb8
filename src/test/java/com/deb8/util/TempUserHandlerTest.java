package com.deb8.util;

import java.util.Objects;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TempUserHandlerTest {
	
	@Test
	public void 학습테스트_오브젝트해쉬결과값확인() {
		for(int k=0; k<50; k++) {
			int i = Objects.hash("8ritbon@gmail.com", "23r23", System.currentTimeMillis());
			int j = Objects.hash("abc@naver.com", "laijfw", System.currentTimeMillis());
			int g = Objects.hash("52332@daum.net", "liawelif", System.currentTimeMillis());
			System.out.println(i);
			System.out.println(j);
			System.out.println(g);
		}
	}
	
	@Test
	public void 비크립트암호화확인() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		int count = 10;
		String passwd = "test1";
		while(count-- > 0) {
			String encodedPasswd = passwordEncoder.encode(passwd);
			System.out.println(encodedPasswd);
		}
	}
}
