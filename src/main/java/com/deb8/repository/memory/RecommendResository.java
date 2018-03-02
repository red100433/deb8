package com.deb8.repository.memory;

import java.util.Set;

import com.deb8.entity.Post;
import com.deb8.entity.Topic;

public class RecommendResository {
	private static RecommendResository INSTANCE = new RecommendResository();
	private Set<Topic> topTopicSet;
	private Set<Post> topPostSet;

	private RecommendResository() {}

	public static RecommendResository getInstance() {
		return INSTANCE;
	}

	public void setTopTopicSet(Set<Topic> topTopicSet) {
		this.topTopicSet = topTopicSet;
	}

	public void setTopPostSet(Set<Post> topPostSet) {
		this.topPostSet = topPostSet;
	}

	public Set<Topic> getTopTopicSet() {
		return topTopicSet;
	}

	public Set<Post> getTopPostSet() {
		return topPostSet;
	}
}