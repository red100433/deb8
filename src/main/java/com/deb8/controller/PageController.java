package com.deb8.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.deb8.entity.Topic;
import com.deb8.entity.User;
import com.deb8.exception.ResourceNotFoundException;
import com.deb8.service.PostService;
import com.deb8.service.StatisticService;
import com.deb8.service.TopicService;
import com.deb8.service.UserService;

@Controller
public class PageController {
	@Autowired
	PostService postService;

	@Autowired
	UserService userService;

	@Autowired
	TopicService topicService;

	@Autowired
	StatisticService statService;

	@GetMapping("/main/post")
	public String showPostMain() {
		return "page/postMain";
	}

	@GetMapping("/main/topic")
	public String showTopicMain() {
		return "page/topicMain";
	}

	@GetMapping("/settings")
	public String showUserSettings(Model model, Principal principal) {
		User user = userService.getOneByEmail(principal.getName());

		model.addAttribute("user", user);
		return "page/userSettings";
	}

	@GetMapping("/search")
	public String showSearchResult(Model model, @RequestParam String keyword) {
		model.addAttribute("keyword", keyword);
		return "page/search";
	}

	@GetMapping("/home")
	public String showLoginUserHome(Model model, Principal principal) {
		return "redirect:/" + userService.getCodeByEmail(principal.getName());
	}

	@GetMapping("/{code}")
	public String showUserHome(Model model, @PathVariable String code) {
		User user = userService.getOneByCode(code);

		if (user == null) {
			throw new ResourceNotFoundException();
		}

		int userId = user.getId();

		model.addAttribute("user", user);
		model.addAttribute("stat", statService.getUserStat(userId));
		model.addAttribute("topicList", statService.getTopicList(userId));
		return "page/userHome";
	}

	@GetMapping("/topic/{code}")
	public String showTopicHome(Model model, @PathVariable String code) {
		Topic topic = topicService.getOneByCode(code);
		int topicId = topic.getId();

		model.addAttribute("topic", topic);
		model.addAttribute("userList", statService.getUserList(topicId));
		return "page/topicHome";
	}
}