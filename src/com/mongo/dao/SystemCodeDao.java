package com.mongo.dao;

import java.util.List;

import com.mongo.entity.SystemCode;

public interface SystemCodeDao extends ComDao<SystemCode>{
	
	public List<SystemCode> findAll();
	
}
