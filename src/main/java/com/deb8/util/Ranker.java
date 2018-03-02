package com.deb8.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deb8.entity.Post;
import com.deb8.entity.Topic;
import com.deb8.model.stat.PostStatistic;
import com.deb8.model.stat.TopicStatistic;
import com.deb8.model.stat.UserStatistic;

/**
 * 데이터베이스에서 최신순, 인기순으로 뽑아온 데이터들 중에서도 
 * 추천할 만한 데이터를 골라 보여주기 위한 모듈
 * 
 * 실제 보여줄 리스트보다 여유있게 DB에서 읽어온 다음
 * 아래의 기준으로 리스트를 재채점해서 상위의 리스트를 반환한다
 * 
 */
public class Ranker {
	// Post 채점 하기 위한 비율
	private static final double POST_HEART_RATIO = 0.2;
	private static final double USER_POPULAR_RATIO = 0.3;
	private static final double POST_RECENT_RATIO = 0.4;
	private static final double TOPIC_POPULAR_RATIO = 0.2;

	// topic 채점 하기 위한 비율
	private static final double TOPIC_RECENT_RATIO = 0.3;
	private static final double TOPIC_HEART_RATIO = 0.2;
	private static final double TOPIC_USER_RATIO = 0.4;
	private static final double TOPIC_POST_RATIO = 0.1;

	// 시간 채점하기 위한 단위 (현재 : 15분)
	private static final int TIME_UNIT = 1000 * 60 * 15;
	private static final int RANK_LIMIT = 10;

	/**
	 * 게시글의 최근 정도, 헤비유저 여부, 주제의 인기도, 포스트의 인기도의 지표를 가지고
	 * 리스트들을 재채점해서 상위의 포스트들만 반환한다
	 * 
	 * @param statMap 후보 포스트들과 포스트의 통계정보들이 담긴 Map
	 * @return 상위 랭크된 포스트의 리스트
	 */
	public static List<Post> rankPosts(Map<Post, PostStatistic> statMap) {
		Map<Post, Double> rankMap = new HashMap<>();

		for (Map.Entry<Post, PostStatistic> entry : statMap.entrySet()) {
			Post post = entry.getKey();
			PostStatistic postStat = entry.getValue();

			rankMap.put(post, getScore(post, postStat));
		}

		return getHighRankedList(rankMap);
	}

	/**
	 * 주제의 최근 정도, 주제의 인기도, 참여자 수, 게시된 생각 수의 지표를 가지고
	 * 리스트들을 재채점해서 상위의 주제들만 반환한다
	 * 
	 * @param statMap 후보 주제들과 주제의 통계정보들이 담긴 Map
	 * @return 상위 랭크된 주제의 리스트
	 */
	public static List<Topic> rankTopics(Map<Topic, TopicStatistic> statMap) {
		Map<Topic, Double> rankMap = new HashMap<>();

		for (Map.Entry<Topic, TopicStatistic> entry : statMap.entrySet()) {
			Topic topic = entry.getKey();
			TopicStatistic topicStat = entry.getValue();

			rankMap.put(topic, getScore(topic, topicStat));
		}

		return getHighRankedList(rankMap);
	}

	// 각 지표마다 반영 비율을 두고 비교 채점한다
	private static double getScore(Post post, PostStatistic postStat) {
		double score = 0;

		UserStatistic userStat = postStat.getUserStat();
		TopicStatistic topicStat = postStat.getTopicStat();
		
		long now = System.currentTimeMillis();

		score += post.getHearts() * POST_HEART_RATIO;
		score += (userStat.getHeartCnt()) * USER_POPULAR_RATIO;
		score += ((now - post.getTime()) / TIME_UNIT) * POST_RECENT_RATIO;
		score += (topicStat.getUserCnt() + topicStat.getHeartCnt()) * TOPIC_POPULAR_RATIO;

		return score;
	}

	// 각 지표마다 반영 비율을 두고 비교 채점한다
	private static double getScore(Topic topic, TopicStatistic topicStat) {
		double score = 0;

		score += topic.getTime() * TOPIC_RECENT_RATIO;
		score += topicStat.getHeartCnt() * TOPIC_HEART_RATIO;
		score += topicStat.getUserCnt() * TOPIC_USER_RATIO;
		score += topicStat.getPostCnt() * TOPIC_POST_RATIO;

		return score;
	}

	// 채점한 map을 고득점 순으로 정렬시키고 상위 결과를 반환한다
	public static <K> List<K> getHighRankedList(Map<K, Double> map) {
		List<Map.Entry<K, Double>> list = new ArrayList<Map.Entry<K, Double>>(map.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<K, Double>>() {
			public int compare(Map.Entry<K, Double> o1, Map.Entry<K, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		List<K> highRankedList = new ArrayList<>();

		for (int i = 0; i < list.size() && i < RANK_LIMIT; i++) {
			highRankedList.add(list.get(i).getKey());
		}

		return highRankedList;
	}

	public static int getRankLimit() {
		return RANK_LIMIT;
	}
}
