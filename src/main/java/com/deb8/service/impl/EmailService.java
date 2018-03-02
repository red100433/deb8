package com.deb8.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

	@Autowired
	public JavaMailSender emailSender;

	public void sendAuthMail(String to, String key) throws MessagingException {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

		mimeMessage.setContent(getContent(key), "text/html; charset=utf-8");

		helper.setTo(to);
		helper.setSubject("deb8 가입 인증메일");

		emailSender.send(mimeMessage);
	}

	public String getContent(String key) {
		return "<h1>deb 가입 인증메일</h1>" +
			"<p>1시간 이내에 아래 링크를 클릭해 가입을 완료해주세요</p>" +
			"<a href=\'http://10.67.8.96:8080/register/check?key=" +
			key + "\'>가입 완료 링크</a>";
	}
}
