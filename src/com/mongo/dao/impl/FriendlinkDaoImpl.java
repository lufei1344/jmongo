package com.mongo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongo.dao.FriendlinkDao;
import com.mongo.entity.Page;
import com.mongo.entity.User;
import com.mongo.entity.cms.Friendlink;

@Repository(value="friendlinkDao")
public class FriendlinkDaoImpl implements FriendlinkDao{

	@Autowired
	private BaseDao baseDao;
	
	
	@Override
	public Page findPage(Page page, Friendlink t) {
		Query query = new Query();
		if(t != null){
			Criteria c =Criteria.where("id").ne("");
			
			query.addCriteria(c);
		}
		return baseDao.findPage(User.class, page, query);
	}
	@Override
	public boolean remove(String id) {
		baseDao.removeOne(Friendlink.class, id);
		return true;
	}
	@Override
	public Friendlink save(Friendlink t) {
		return (Friendlink)baseDao.save(t);
	}
	@Override
	public Friendlink find(String id) {
		return baseDao.findOne(Friendlink.class, id);
	}
	@Override
	public Friendlink update(Friendlink t) {
		baseDao.findAndModify(t.getId(), t);
		return t;
	}
	@Override
	public List<Friendlink> findAll() {
		return null;
	}
	
}
