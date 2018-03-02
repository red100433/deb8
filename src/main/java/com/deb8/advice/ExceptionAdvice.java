package com.deb8.advice;

import javax.mail.MessagingException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.deb8.exception.InvalidRequestException;

@ControllerAdvice
public class ExceptionAdvice {
	@ExceptionHandler(value = {MessagingException.class, InvalidRequestException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleException(Exception e) {
		return e.getMessage();
	}
}