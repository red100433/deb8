package com.deb8.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deb8.entity.Post;
import com.deb8.entity.Topic;
import com.deb8.model.Paging;
import com.deb8.model.SimplePost;
import com.deb8.service.PostService;
import com.deb8.service.TopicService;
import com.deb8.service.UserService;

@RestController
public class PostController {
	@Autowired
	PostService postService;

	@Autowired
	TopicService topicService;

	@Autowired
	UserService userService;

	@MessageMapping("/topic/{code}/create")
	@SendTo("/topic/{code}")
	public Post addPost(@DestinationVariable String code, @RequestParam SimplePost simplePost, Principal principal) {
		String loginUserCode = userService.getCodeByEmail(principal.getName());
		
		simplePost.setUserCode(loginUserCode);
		return postService.add(simplePost);
	}

	@MessageMapping("/{code}/created")
	@SendTo("/{code}")
	public Post showPostToUserHome(@DestinationVariable String code, Post post) {
		return post;
	}

	@GetMapping("/topic/{code}/get/{lastId}")
	public List<Post> getPostByTopicCode(@PathVariable String code, @PathVariable long lastId) {
		return postService.getSomeByTopic(new Paging(code, lastId));
	}

	@GetMapping("/{code}/get/{lastId}")
	public List<Post> getPostByUserCode(@PathVariable String code, @PathVariable long lastId) {
		return postService.getSomeByUser(new Paging(code, lastId));
	}

	@PostMapping("/topic/create")
	public Topic addPost(Topic topic, Principal principal) {
		int loginUserId = getLoginUserId(principal);
		topic.setWriterId(loginUserId);
		return topicService.add(topic);
	}

	@PutMapping("/topic/update")
	public boolean modifyTitle(Topic topic, Principal principal) {
		int loginUserId = getLoginUserId(principal);
		return topicService.modifyTitle(topic, loginUserId);
	}

	@DeleteMapping("/topic/delete/{topicId}")
	public boolean removeTopic(@PathVariable int topicId, Principal principal) {
		int loginUserId = getLoginUserId(principal);
		return topicService.remove(topicId, loginUserId);
	}

	@PutMapping("/post/update")
	public boolean modifyContents(Post post, Principal principal) {
		int loginUserId = getLoginUserId(principal);
		return postService.modifyContents(post, loginUserId);
	}

	@DeleteMapping("/post/delete/{postId}")
	public boolean removePost(@PathVariable int postId, Principal principal) {
		int loginUserId = getLoginUserId(principal);
		return postService.remove(postId, loginUserId);
	}

	@GetMapping("/settings/get/topiclist")
	public List<Topic> getTopicListByUser(Principal principal) {
		int writerId = getLoginUserId(principal);
		return topicService.getTopicListByWriterId(writerId);
	}
	
	private int getLoginUserId(Principal principal) {
		return userService.getIdByEmail(principal.getName());
	}
}