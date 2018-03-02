package com.deb8.exception;

public enum ExceptionMessage {
	WRONG_EMAIL("이메일 형식에 맞지 않습니다"),
	INVALID_USER("없거나 탈퇴한 유저입니다"),
	INVALID_POST("삭제되었거나 존재하지 않는 포스트입니다"),
	INVALID_TOPIC("삭제되었거나 존재하지 않는 주제입니다"),
	EXCEED_LIMIT("입력 가능한 길이를 초과했습니다"),
	EMPTY_CONTENTS("내용을 입력해주세요"),
	EXPIRED_KEY("인증키가 만료되었습니다. 새로 인증 받아주세요."),
	INVALID_PASSWD_LENGTH("비밀번호는 5자 이상 15자 이하여야 합니다"),
	INVALID_AUTH("이 기능을 수행할 권한이 없습니다");

	private String message;

	ExceptionMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}