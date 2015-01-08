package com.mongo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongo.dao.AdSlotDao;
import com.mongo.entity.Page;
import com.mongo.entity.cms.AdSlot;

@Repository(value="adSlotDao")
public class AdSlotDaoImpl implements AdSlotDao{

	@Autowired
	private BaseDao baseDao;

	

	@Override
	public AdSlot find(String id) {
		return baseDao.findOne(AdSlot.class, id);
	}

	@Override
	public Page findPage(Page page, AdSlot t) {
		Query query = new Query();
		return baseDao.findPage(AdSlot.class, page, query);
	}

	@Override
	public boolean remove(String id) {
		baseDao.removeOne(AdSlot.class, id);
		return true;
	}

	@Override
	public AdSlot save(AdSlot t) {
		return (AdSlot)baseDao.save(t);
	}

	@Override
	public AdSlot update(AdSlot t) {
		baseDao.findAndModify(t.getId(), t);
		return t;
	}

	
	
}
