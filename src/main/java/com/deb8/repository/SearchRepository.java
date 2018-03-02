package com.deb8.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deb8.entity.Post;
import com.deb8.entity.Topic;
import com.deb8.model.Paging;

@Mapper
public interface SearchRepository {
	public List<Post> readSomePostResult(Paging paging);

	public List<Topic> readSomeTopicResult(Paging paging);
}