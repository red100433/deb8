package com.deb8.service;

import java.util.List;

import com.deb8.entity.Post;
import com.deb8.entity.Topic;
import com.deb8.model.Paging;

public interface SearchService {
	public List<Post> getSomePostResult(Paging paging);

	public List<Topic> getSomeTopicResult(Paging paging);
}