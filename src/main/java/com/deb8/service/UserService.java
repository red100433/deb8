package com.deb8.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.deb8.entity.User;

public interface UserService extends UserDetailsService {

	public void add(String authKey);

	public User getOneByCode(String code);

	public User getOneByEmail(String email);

	public String getCodeByEmail(String email);

	public int getIdByEmail(String email);

	public boolean modifyBio(User user);

	public boolean modifyPasswd(User user);

	public boolean remove(int id);

	public boolean isEmailAvailable(String email);

	public boolean isValidAuthKey(String key);

	public boolean isValid(User user);
}