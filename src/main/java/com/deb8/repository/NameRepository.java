package com.deb8.repository;

import org.apache.ibatis.annotations.Mapper;

import com.deb8.model.NameSet;

@Mapper
public interface NameRepository {
	
	boolean isAssigned(NameSet nameSet);
	
	String readOne(NameSet nameSet);
}
