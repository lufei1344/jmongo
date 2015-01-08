package com.mongo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongo.dao.ChannelDao;
import com.mongo.entity.Page;
import com.mongo.entity.cms.Channel;

@Repository(value="channelDao")
public class ChannelDaoImpl implements ChannelDao{

	@Autowired
	private BaseDao baseDao;

	

	@Override
	public Channel find(String id) {
		return baseDao.findOne(Channel.class, id);
	}

	@Override
	public Page findPage(Page page, Channel t) {
		Query query = new Query();
		return baseDao.findPage(Channel.class, page, query);
	}

	@Override
	public boolean remove(String id) {
		baseDao.removeOne(Channel.class, id);
		return true;
	}

	@Override
	public Channel save(Channel t) {
		return (Channel)baseDao.save(t);
	}

	@Override
	public Channel update(Channel t) {
		baseDao.findAndModify(t.getId(), t);
		return t;
	}

	@Override
	public List<Channel> findAll() {
		return baseDao.find(Channel.class);
	}

	
	
}
