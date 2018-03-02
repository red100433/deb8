package com.deb8.service;

import java.util.List;

import com.deb8.entity.Topic;

public interface TopicService {
	public Topic add(Topic topic);

	public Topic getOneByCode(String code);

	public Topic getOneById(int id);

	public List<Topic> getTopicListByWriterId(int writerId);

	public List<Topic> getRecentList(int limit);

	public List<Topic> getPopularList(int limit);

	public boolean modifyTitle(Topic topic, int loginUserId);

	public boolean remove(int id, int loginUserId);
}