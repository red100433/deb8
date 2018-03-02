package com.deb8.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deb8.entity.Post;
import com.deb8.exception.ExceptionMessage;
import com.deb8.exception.InvalidRequestException;
import com.deb8.model.Paging;
import com.deb8.model.SimplePost;
import com.deb8.repository.PostRepository;
import com.deb8.repository.TopicRepository;
import com.deb8.service.PostService;
import com.deb8.util.StringValidator;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepo;

	@Autowired
	TopicRepository topicRepo;

	@Override
	public Post add(SimplePost simplePost) throws InvalidRequestException {
		if (isValid(simplePost) == false) {
			throw new InvalidRequestException(ExceptionMessage.INVALID_TOPIC);
		}

		StringValidator.checkContents(simplePost.getContents());

		simplePost.setTime(System.currentTimeMillis());
		postRepo.create(simplePost);

		return getOne(simplePost.getId());
	}

	@Override
	public Post getOne(int id) {
		return postRepo.readById(id);
	}

	@Override
	public List<Post> getRecentList(int limit) {
		return postRepo.readRecentPost(limit);
	}

	@Override
	public List<Post> getPopularList(int limit) {
		return postRepo.readPopularPost(limit);
	}

	@Override
	public List<Post> getSomeByTopic(Paging paging) {
		return postRepo.readSomeByTopic(paging);
	}

	@Override
	public List<Post> getSomeByUser(Paging paging) {
		return postRepo.readSomeByUser(paging);
	}

	@Override
	public boolean modifyContents(Post post, int loginUserId) throws InvalidRequestException {
		int postId = post.getId();
		int writerIdOfPost = postRepo.readWriterId(postId);

		if (writerIdOfPost != loginUserId) {
			throw new InvalidRequestException(ExceptionMessage.INVALID_AUTH);
		}

		StringValidator.checkContents(post.getContents());
		boolean success = postRepo.updateContents(post) > 0;
		return success;
	}

	@Override
	public boolean remove(int id, int loginUserId) {
		int writerIdOfPost = postRepo.readWriterId(id);

		if (writerIdOfPost != loginUserId) {
			throw new InvalidRequestException(ExceptionMessage.INVALID_AUTH);
		}

		boolean success = postRepo.delete(id) > 0;
		return success;
	}

	private boolean isValid(SimplePost simplePost) {
		String topicCode = simplePost.getTopicCode();

		return topicRepo.hasCode(topicCode);
	}
}