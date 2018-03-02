package com.deb8.service;

import java.util.List;

import com.deb8.entity.Heart;

public interface HeartService {

	public boolean add(Heart heart);

	public List<Integer> getHeartedPostIds(int giverId);

	public boolean remove(Heart heart);
}