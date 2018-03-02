package com.deb8.util;

import java.security.SecureRandom;

import com.deb8.model.NameSet;

public class NameSetGenerator {
	private static final int ADJECTIVE_POOL_MAX = 179;
	private static final int ANIMAL_POOL_MAX = 2258;
	
	public static NameSet getRandomeNameSet() {
		SecureRandom secureRandom = new SecureRandom();
		NameSet nameSet = new NameSet();

		int adjectiveId = secureRandom.nextInt(ADJECTIVE_POOL_MAX);
		int animalId = secureRandom.nextInt(ANIMAL_POOL_MAX);

		nameSet.setAdjectiveId(adjectiveId);
		nameSet.setAnimalId(animalId);
		
		return nameSet;
	}
}
