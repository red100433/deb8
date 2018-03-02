package com.deb8.service;

import java.util.List;

import com.deb8.entity.Topic;
import com.deb8.model.SimpleUser;
import com.deb8.model.stat.TopicStatistic;
import com.deb8.model.stat.UserStatistic;

public interface StatisticService {

	public UserStatistic getUserStat(int userId);

	public TopicStatistic getTopicStat(int topicId);

	public List<Topic> getTopicList(int userId);

	public List<SimpleUser> getUserList(int topicId);
}