package com.deb8.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException auth)
		throws IOException, ServletException {
		req.setAttribute("errorMessage", "아이디나 패스워드가 잘못되었습니다.");
		req.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(req, resp);
	}
}