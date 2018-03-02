package com.deb8.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.deb8.entity.User;
import com.deb8.exception.InvalidRequestException;
import com.deb8.model.NameSet;
import com.deb8.model.TempUser;
import com.deb8.repository.NameRepository;
import com.deb8.repository.UserRepository;
import com.deb8.repository.memory.TempUserRepository;
import com.deb8.service.UserService;
import com.deb8.util.CodeGenerator;
import com.deb8.util.NameSetGenerator;
import com.deb8.util.StringValidator;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	NameRepository nameRepo;

	@Override
	public void add(String authKey) {
		TempUser tempUser = TempUserRepository.getInstance().getTempUserByKey(authKey);
		User user = makeUserByTempUser(tempUser);
		
		userRepo.create(user);
	}

	@Override
	public User getOneByCode(String code) {
		return userRepo.readByCode(code);
	}

	@Override
	public User getOneByEmail(String email) {
		return userRepo.readByEmail(email);
	}

	@Override
	public String getCodeByEmail(String email) {
		return getOneByEmail(email).getCode();
	}

	@Override
	public int getIdByEmail(String email) {
		return getOneByEmail(email).getId();
	}

	@Override
	public boolean modifyBio(User user) throws InvalidRequestException {
		StringValidator.checkBio(user.getBio());

		boolean success = userRepo.updateBio(user) > 0;
		return success;
	}

	@Override
	public boolean modifyPasswd(User user) throws InvalidRequestException {
		String plainPasswd = user.getPasswd();
		String encodedPasswd = getEncodedPasswd(plainPasswd);

		StringValidator.checkPassword(plainPasswd);

		user.setPasswd(encodedPasswd);

		boolean success = userRepo.updatePasswd(user) > 0;
		return success;
	}

	@Override
	public boolean remove(int id) {
		boolean success = userRepo.delete(id) > 0;
		return success;
	}

	@Override
	public boolean isValid(User user) {
		User existingUser = getOneByEmail(user.getEmail());

		String givenPasswd = user.getPasswd();
		String existingPasswd = existingUser.getPasswd();

		boolean isSame = BCrypt.checkpw(givenPasswd, existingPasswd);
		return isSame;
	}

	@Override
	public boolean isEmailAvailable(String email) {
		if (getOneByEmail(email) == null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isValidAuthKey(String key) {
		boolean hasKey = TempUserRepository.getInstance().has(key);
		return hasKey;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.readByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("login fail");
		}

		return user;
	}

	private User makeUserByTempUser(TempUser tempUser) {
		User user = new User();

		long now = System.currentTimeMillis();
		String encryptedPasswd = getEncodedPasswd(tempUser.getPasswd());
		String uniqueCode = getUniqueCode();
		String generatedName = getRandomName();

		user.setName(generatedName);
		user.setEmail(tempUser.getEmail());
		user.setPasswd(encryptedPasswd);
		user.setTime(now);
		user.setCode(uniqueCode);

		return user;
	}

	private String getEncodedPasswd(String passwd) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPasswd = passwordEncoder.encode(passwd);

		return encodedPasswd;
	}

	private String getUniqueCode() {
		String code = CodeGenerator.generateCode();

		while (userRepo.readByCode(code) != null) {
			code = CodeGenerator.generateCode();
		}

		return code;
	}
	
	private String getRandomName() {
		NameSet nameSet = NameSetGenerator.getRandomeNameSet();
		
		while(nameRepo.isAssigned(nameSet)) {
			nameSet = NameSetGenerator.getRandomeNameSet();
		}
		
		return nameRepo.readOne(nameSet);
	}
}