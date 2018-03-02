package com.deb8.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deb8.entity.User;
import com.deb8.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/user/get")
	public User getUser(Principal principal) {
		return userService.getOneByEmail(principal.getName());
	}

	@GetMapping("/passwd/check")
	public boolean isValidPasswd(@RequestParam String passwd, Principal principal) {
		User user = new User();
		user.setEmail(principal.getName());
		user.setPasswd(passwd);

		return userService.isValid(user);
	}

	@PutMapping("/user/update/bio")
	public boolean modifyBio(User user, Principal principal) {
		int id = userService.getIdByEmail(principal.getName());
		user.setId(id);

		return userService.modifyBio(user);
	}

	@PutMapping("/user/update/passwd")
	public boolean modifyPasswd(User user, Principal principal) {
		int id = userService.getIdByEmail(principal.getName());
		String passwd = user.getPasswd();

		user.setId(id);
		user.setPasswd(passwd);

		return userService.modifyPasswd(user);
	}

	@DeleteMapping("/user/delete")
	public boolean removeUser(Principal principal) {
		int id = userService.getIdByEmail(principal.getName());

		return userService.remove(id);
	}
}