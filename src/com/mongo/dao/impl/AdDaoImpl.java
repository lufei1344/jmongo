package com.mongo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongo.dao.AdDao;
import com.mongo.entity.Page;
import com.mongo.entity.cms.Ad;

@Repository(value="adDao")
public class AdDaoImpl implements AdDao{

	@Autowired
	private BaseDao baseDao;

	

	@Override
	public Ad find(String id) {
		return baseDao.findOne(Ad.class, id);
	}

	@Override
	public Page findPage(Page page, Ad t) {
		Query query = new Query();
		return baseDao.findPage(Ad.class, page, query);
	}

	@Override
	public boolean remove(String id) {
		baseDao.removeOne(Ad.class, id);
		return true;
	}

	@Override
	public Ad save(Ad t) {
		return (Ad)baseDao.save(t);
	}

	@Override
	public Ad update(Ad t) {
		baseDao.findAndModify(t.getId(), t);
		return t;
	}

	
	
}
