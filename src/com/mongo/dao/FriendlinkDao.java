package com.mongo.dao;

import java.util.List;

import com.mongo.entity.cms.Friendlink;

public interface FriendlinkDao extends ComDao<Friendlink>{
	
	
	public List<Friendlink> findAll();
	
	
}
