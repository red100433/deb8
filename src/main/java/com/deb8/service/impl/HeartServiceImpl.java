package com.deb8.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deb8.entity.Heart;
import com.deb8.exception.ExceptionMessage;
import com.deb8.exception.InvalidRequestException;
import com.deb8.repository.HeartRepository;
import com.deb8.repository.PostRepository;
import com.deb8.service.HeartService;

@Service
public class HeartServiceImpl implements HeartService {

	@Autowired
	HeartRepository heartRepo;

	@Autowired
	PostRepository postRepo;

	@Override
	public boolean add(Heart heart) throws InvalidRequestException {
		if (isValid(heart) == false) {
			throw new InvalidRequestException(ExceptionMessage.INVALID_POST);
		}

		long now = System.currentTimeMillis();

		heart.setTime(now);
		heartRepo.create(heart);

		boolean success = heart.getId() > 0;
		return success;
	}

	@Override
	public List<Integer> getHeartedPostIds(int giverId) {
		return heartRepo.readHeartedPostIds(giverId);
	}

	@Override
	public boolean remove(Heart heart) throws InvalidRequestException {
		if (isValid(heart) == false) {
			throw new InvalidRequestException(ExceptionMessage.INVALID_POST);
		}

		boolean success = heartRepo.delete(heart) > 0;
		return success;
	}

	private boolean isValid(Heart heart) {
		int id = heart.getPostId();

		return postRepo.hasId(id);
	}
}