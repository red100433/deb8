package com.deb8.cron;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.deb8.entity.Post;
import com.deb8.entity.Topic;
import com.deb8.repository.memory.RecommendResository;
import com.deb8.service.impl.RecommendServiceImpl;

@Component
public class RecommendScheduler {
	@Autowired
	RecommendServiceImpl recommendService;

	@Scheduled(cron = "0 */5 * * * *")
	public void setRecommendList() {
		Set<Post> topPostSet = recommendService.getRecommendPosts();
		Set<Topic> topTopicSet = recommendService.getRecommendTopics();

		RecommendResository.getInstance().setTopPostSet(topPostSet);
		RecommendResository.getInstance().setTopTopicSet(topTopicSet);
	}
}