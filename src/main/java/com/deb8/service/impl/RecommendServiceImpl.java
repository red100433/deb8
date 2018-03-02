package com.deb8.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deb8.entity.Post;
import com.deb8.entity.Topic;
import com.deb8.model.stat.PostStatistic;
import com.deb8.model.stat.TopicStatistic;
import com.deb8.service.RecommendService;
import com.deb8.util.Ranker;

@Service
public class RecommendServiceImpl implements RecommendService {
	private static final int NUM_OF_CANDIDATE = 10 * Ranker.getRankLimit();

	@Autowired
	PostServiceImpl postService;

	@Autowired
	TopicServiceImpl topicService;

	@Autowired
	StatisticServiceImpl statService;

	@Override
	public Set<Post> getRecommendPosts() {
		Set<Post> postSet = new HashSet<>();

		postSet.addAll(getMostRecentPosts());
		postSet.addAll(getMostPouplarPosts());

		return postSet;
	}

	@Override
	public Set<Topic> getRecommendTopics() {
		Set<Topic> topicSet = new HashSet<>();

		topicSet.addAll(getRecentTopics());
		topicSet.addAll(getMostPouplarTopics());

		return topicSet;
	}

	private List<Post> getMostRecentPosts() {
		return getRankedPostList(postService.getRecentList(NUM_OF_CANDIDATE));
	}

	private List<Post> getMostPouplarPosts() {
		return getRankedPostList(postService.getPopularList(NUM_OF_CANDIDATE));
	}

	private List<Topic> getRecentTopics() {
		return getRankedTopicList(topicService.getRecentList(NUM_OF_CANDIDATE));
	}

	private List<Topic> getMostPouplarTopics() {
		return getRankedTopicList(topicService.getPopularList(NUM_OF_CANDIDATE));
	}

	/**
	 * 추천할 만한 후보 리스트를 바탕으로 그 중에서도 신뢰성있는 포스트의 리스트를 반환한다
	 * 
	 * @param 채점하고 싶은 후보 리스트
	 * @return 상위 랭크된 리스트
	 */
	private List<Post> getRankedPostList(List<Post> candidatList) {
		Map<Post, PostStatistic> statMap = new HashMap<>();

		for (Post post : candidatList) {
			PostStatistic postStat = new PostStatistic();

			int topicId = post.getTopic().getId();
			int userId = post.getWriter().getId();

			postStat.setTopicStat(statService.getTopicStat(topicId));
			postStat.setUserStat(statService.getUserStat(userId));

			statMap.put(post, postStat);
		}

		return Ranker.rankPosts(statMap);
	}

	/**
	 * 추천할 만한 후보 리스트를 바탕으로 그 중에서도 신뢰성있는 주제의 리스트를 반환한다
	 * 
	 * @param 채점하고 싶은 후보 리스트
	 * @return 상위 랭크된 리스트
	 */
	private List<Topic> getRankedTopicList(List<Topic> candidatList) {
		Map<Topic, TopicStatistic> statMap = new HashMap<>();

		for (Topic topic : candidatList) {
			statMap.put(topic, statService.getTopicStat(topic.getId()));
		}

		return Ranker.rankTopics(statMap);
	}
}
