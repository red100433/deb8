package com.deb8.service;

import java.util.List;

import com.deb8.entity.Post;
import com.deb8.model.Paging;
import com.deb8.model.SimplePost;

public interface PostService {
	public Post add(SimplePost simplePost);

	public Post getOne(int id);

	public List<Post> getRecentList(int limit);

	public List<Post> getPopularList(int limit);

	public List<Post> getSomeByTopic(Paging paging);

	public List<Post> getSomeByUser(Paging paging);

	public boolean modifyContents(Post post, int loginUserId);

	public boolean remove(int id, int loginUserId);
}