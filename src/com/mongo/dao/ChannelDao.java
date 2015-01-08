package com.mongo.dao;

import java.util.List;

import com.mongo.entity.cms.Channel;

public interface ChannelDao extends ComDao<Channel>{
	
	public List<Channel> findAll();
}
