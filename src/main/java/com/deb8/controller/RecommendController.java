package com.deb8.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deb8.entity.Post;
import com.deb8.entity.Topic;
import com.deb8.repository.memory.RecommendResository;
import com.deb8.service.RecommendService;

@RestController
@RequestMapping("/recommend")
public class RecommendController {

	@Autowired
	RecommendService recommendService;

	@GetMapping("/post")
	public Set<Post> getMostRecentPosts() {
		return RecommendResository.getInstance().getTopPostSet();
	}

	@GetMapping("/topic")
	public Set<Topic> getRandomTopics() {
		return RecommendResository.getInstance().getTopTopicSet();
	}
}