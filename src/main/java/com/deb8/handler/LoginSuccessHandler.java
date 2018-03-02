package com.deb8.handler;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.deb8.entity.User;
import com.deb8.service.UserService;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	UserService userService;

	public LoginSuccessHandler(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth)
		throws IOException, ServletException {

		String email = (String)req.getParameter("username");
		User user = userService.getOneByEmail(email);
		String code = user.getCode();

		Cookie codeCookie = new Cookie("code", URLEncoder.encode(code, "UTF-8"));

		resp.addCookie(codeCookie);
		resp.sendRedirect("/home");
	}
}