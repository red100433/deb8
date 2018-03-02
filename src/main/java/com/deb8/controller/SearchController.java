package com.deb8.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deb8.entity.Post;
import com.deb8.entity.Topic;
import com.deb8.model.Paging;
import com.deb8.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {
	@Autowired
	SearchService searchService;

	@GetMapping("/post/{keyword}/{lastId}")
	@ResponseBody
	public List<Post> getMorePostResult(@PathVariable String keyword, @PathVariable long lastId) {
		return searchService.getSomePostResult(new Paging(keyword, lastId));
	}

	@GetMapping("/topic/{keyword}/{lastId}")
	@ResponseBody
	public List<Topic> getMoreTopicResult(@PathVariable String keyword, @PathVariable long lastId) {
		return searchService.getSomeTopicResult(new Paging(keyword, lastId));
	}
}
