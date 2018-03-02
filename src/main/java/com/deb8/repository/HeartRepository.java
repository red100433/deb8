package com.deb8.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deb8.entity.Heart;

@Mapper
public interface HeartRepository {
	public void create(Heart heart);

	public List<Integer> readHeartedPostIds(int giverId);

	public int delete(Heart heart);
}