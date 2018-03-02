package com.deb8.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deb8.entity.Topic;
import com.deb8.exception.ExceptionMessage;
import com.deb8.exception.InvalidRequestException;
import com.deb8.model.SimpleUser;
import com.deb8.model.stat.TopicStatistic;
import com.deb8.model.stat.UserStatistic;
import com.deb8.repository.StatisticRepository;
import com.deb8.repository.TopicRepository;
import com.deb8.repository.UserRepository;
import com.deb8.service.StatisticService;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Autowired
	StatisticRepository statRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	TopicRepository topicRepo;

	@Override
	public UserStatistic getUserStat(int userId) throws InvalidRequestException {
		if (userRepo.hasId(userId) == false) {
			throw new InvalidRequestException(ExceptionMessage.INVALID_TOPIC);
		}

		return statRepo.readUserStat(userId);
	}

	@Override
	public TopicStatistic getTopicStat(int topicId) throws InvalidRequestException {
		if (topicRepo.hasId(topicId) == false) {
			throw new InvalidRequestException(ExceptionMessage.INVALID_TOPIC);
		}

		return statRepo.readTopicStat(topicId);
	}

	@Override
	public List<Topic> getTopicList(int userId) throws InvalidRequestException {
		if (userRepo.hasId(userId) == false) {
			throw new InvalidRequestException(ExceptionMessage.INVALID_TOPIC);
		}

		return statRepo.readAllTopicByUserId(userId);
	}

	@Override
	public List<SimpleUser> getUserList(int topicId) throws InvalidRequestException {
		if (topicRepo.hasId(topicId) == false) {
			throw new InvalidRequestException(ExceptionMessage.INVALID_TOPIC);
		}

		return statRepo.readAllUserByTopicId(topicId);
	}
}