package com.deb8.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deb8.entity.Heart;
import com.deb8.service.HeartService;
import com.deb8.service.UserService;

@RestController
@RequestMapping("/heart")
public class HeartController {
	@Autowired
	HeartService heartService;

	@Autowired
	UserService userService;

	@GetMapping
	public List<Integer> getHeartedPostIds(Principal principal) {
		int giverId = userService.getIdByEmail(principal.getName());
		return heartService.getHeartedPostIds(giverId);
	}

	@PostMapping("/{postId}")
	public boolean addHeart(@PathVariable int postId, Principal principal) {
		Heart heart = makeHeartWithRequest(principal, postId);
		return heartService.add(heart);
	}

	@DeleteMapping("/{postId}")
	public boolean removeHeart(@PathVariable int postId, Principal principal) {
		Heart heart = makeHeartWithRequest(principal, postId);
		return heartService.remove(heart);
	}
	
	private Heart makeHeartWithRequest(Principal principal, int postId) {
		int giverId = userService.getIdByEmail(principal.getName());

		Heart heart = new Heart();
		heart.setGiverId(giverId);
		heart.setPostId(postId);
		
		return heart;
	}
}