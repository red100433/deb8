package com.deb8.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deb8.model.TempUser;
import com.deb8.repository.memory.TempUserRepository;
import com.deb8.service.UserService;
import com.deb8.service.impl.EmailService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;

	@GetMapping("/login")
	public String showLoginMain() {
		return "user/login";
	}

	@GetMapping("/register")
	public String showRegisterForm() {
		return "user/register";
	}

	@PostMapping("/sendmail")
	@ResponseBody
	public boolean sendEmail(TempUser tempUser) throws MessagingException {
		boolean isEmailAvailable = userService.isEmailAvailable(tempUser.getEmail());

		if (isEmailAvailable) {
			String key = TempUserRepository.getInstance().setTempUser(tempUser);
			emailService.sendAuthMail(tempUser.getEmail(), key);
		}

		return isEmailAvailable;
	}

	@GetMapping("/register/check")
	public String registerCheck(RedirectAttributes redirectAttributes, @RequestParam String key) {
		if (userService.isValidAuthKey(key)) {
			userService.add(key);
			redirectAttributes.addFlashAttribute("registerMessage", "회원 가입이 완료되었습니다. 로그인 하세요");
		} else {
			redirectAttributes.addFlashAttribute("registerMessage", "인증 토큰이 만료되었습니다. 다시 가입해주세요");
		}

		return "redirect:/login";
	}
}