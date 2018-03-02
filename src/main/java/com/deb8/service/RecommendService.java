package com.deb8.service;

import java.util.Set;

import com.deb8.entity.Post;
import com.deb8.entity.Topic;

public interface RecommendService {

	public Set<Post> getRecommendPosts();

	public Set<Topic> getRecommendTopics();
}
