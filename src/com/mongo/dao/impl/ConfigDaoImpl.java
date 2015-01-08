package com.mongo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongo.dao.ConfigDao;
import com.mongo.entity.Page;
import com.mongo.entity.cms.Config;

@Repository(value="configDao")
public class ConfigDaoImpl implements ConfigDao{

	@Autowired
	private BaseDao baseDao;

	

	@Override
	public Config find(String id) {
		return baseDao.findOne(Config.class, id);
	}

	@Override
	public Page findPage(Page page, Config t) {
		Query query = new Query();
		return baseDao.findPage(Config.class, page, query);
	}

	@Override
	public boolean remove(String id) {
		baseDao.removeOne(Config.class, id);
		return true;
	}

	@Override
	public Config save(Config t) {
		return (Config)baseDao.save(t);
	}

	@Override
	public Config update(Config t) {
		baseDao.findAndModify(t.getId(), t);
		return t;
	}

	
	
}
