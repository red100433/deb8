package com.deb8.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deb8.entity.Post;
import com.deb8.entity.Topic;
import com.deb8.exception.InvalidRequestException;
import com.deb8.model.Paging;
import com.deb8.repository.SearchRepository;
import com.deb8.service.SearchService;
import com.deb8.util.StringValidator;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	SearchRepository searchRepo;

	@Override
	public List<Post> getSomePostResult(Paging paging) throws InvalidRequestException {
		StringValidator.checkKeyword(paging.getTarget());
		return searchRepo.readSomePostResult(paging);
	}

	@Override
	public List<Topic> getSomeTopicResult(Paging paging) throws InvalidRequestException {
		StringValidator.checkKeyword(paging.getTarget());
		return searchRepo.readSomeTopicResult(paging);
	}
}