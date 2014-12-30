package com.mongo.dao;

import java.util.List;

import com.mongo.entity.Menu;

public interface MenuDao extends ComDao<Menu>{
	
	public List<Menu> getUserMenu(String uid);
	
	public List<Menu> findAll();
	
	
}
