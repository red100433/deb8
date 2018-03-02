package com.deb8.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deb8.entity.Topic;

@Mapper
public interface TopicRepository {
	public int create(Topic topic);

	public Topic readByCode(String code);

	public Topic readById(int id);

	public List<Topic> readTopicListByWriterId(int writerId);

	public List<Topic> readRecentList(int limit);

	public List<Integer> readPopularTopicIds(int limit);

	public int readWriterId(int id);

	public int updateTitle(Topic topic);

	public int delete(int id);

	public boolean hasId(int id);

	public boolean hasCode(String code);
}