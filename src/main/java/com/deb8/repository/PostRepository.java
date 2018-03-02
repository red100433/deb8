package com.deb8.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deb8.entity.Post;
import com.deb8.model.Paging;
import com.deb8.model.SimplePost;

@Mapper
public interface PostRepository {
	public void create(SimplePost post);

	public Post readById(int id);

	public List<Post> readRecentPost(int limit);

	public List<Post> readPopularPost(int limit);

	public List<Post> readSomeByTopic(Paging paging);

	public List<Post> readSomeByUser(Paging paging);

	public int readWriterId(int id);

	public int updateContents(Post post);

	public int delete(int id);

	public boolean hasId(int id);
}