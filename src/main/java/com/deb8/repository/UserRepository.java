package com.deb8.repository;

import org.apache.ibatis.annotations.Mapper;

import com.deb8.entity.User;

@Mapper
public interface UserRepository {
	public void create(User user);

	public User readByCode(String code);

	public User readByEmail(String email);

	public int updateBio(User user);

	public int updatePasswd(User user);

	public int delete(int id);

	public boolean hasId(int id);

	public boolean hasUser(User user);
}