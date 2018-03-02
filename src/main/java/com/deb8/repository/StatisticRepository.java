package com.deb8.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deb8.entity.Topic;
import com.deb8.model.SimpleUser;
import com.deb8.model.stat.TopicStatistic;
import com.deb8.model.stat.UserStatistic;

@Mapper
public interface StatisticRepository {
	public UserStatistic readUserStat(int userId);

	public TopicStatistic readTopicStat(int topicId);

	public List<Topic> readAllTopicByUserId(int userId);

	public List<SimpleUser> readAllUserByTopicId(int topicId);
}