package com.deb8.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deb8.entity.Topic;
import com.deb8.exception.ExceptionMessage;
import com.deb8.exception.InvalidRequestException;
import com.deb8.repository.StatisticRepository;
import com.deb8.repository.TopicRepository;
import com.deb8.service.TopicService;
import com.deb8.util.CodeGenerator;
import com.deb8.util.StringValidator;

@Service
public class TopicServiceImpl implements TopicService {
	@Autowired
	TopicRepository topicRepo;

	@Autowired
	StatisticRepository statRepo;

	@Override
	public Topic add(Topic topic) throws InvalidRequestException {
		StringValidator.checkTitle(topic.getTitle());
		topic.setTime(System.currentTimeMillis());

		// 중복되지 않는 code 생성
		String code = CodeGenerator.generateCode();
		while (getOneByCode(code) != null) {
			code = CodeGenerator.generateCode();
		}

		topic.setCode(code);
		topicRepo.create(topic);

		return getOneById(topic.getId());
	}

	@Override
	public Topic getOneByCode(String code) {
		return topicRepo.readByCode(code);
	}

	@Override
	public List<Topic> getTopicListByWriterId(int writerId) {
		return topicRepo.readTopicListByWriterId(writerId);
	}

	@Override
	public List<Topic> getRecentList(int limit) {
		return topicRepo.readRecentList(limit);
	}

	@Override
	public List<Topic> getPopularList(int limit) {
		List<Topic> popularList = new ArrayList<>();

		List<Integer> topicIdList = getPopularTopicIdList(limit);
		for (int id : topicIdList) {
			popularList.add(getOneById(id));
		}

		return popularList;
	}

	@Override
	public boolean modifyTitle(Topic topic, int loginUserId) throws InvalidRequestException {
		StringValidator.checkTitle(topic.getTitle());

		int topicId = topic.getId();
		int writerIdOfTopic = topicRepo.readWriterId(topicId);

		if (writerIdOfTopic != loginUserId) {
			throw new InvalidRequestException(ExceptionMessage.INVALID_AUTH);
		}

		boolean success = topicRepo.updateTitle(topic) > 0;
		return success;
	}

	@Override
	public boolean remove(int id, int loginUserId) {
		int writerIdOfTopic = topicRepo.readWriterId(id);

		if (writerIdOfTopic != loginUserId) {
			throw new InvalidRequestException(ExceptionMessage.INVALID_AUTH);
		}

		boolean success = topicRepo.delete(id) > 0;
		return success;
	}

	private List<Integer> getPopularTopicIdList(int limit) {
		return topicRepo.readPopularTopicIds(limit);
	}

	public Topic getOneById(int id) {
		return topicRepo.readById(id);
	}
}