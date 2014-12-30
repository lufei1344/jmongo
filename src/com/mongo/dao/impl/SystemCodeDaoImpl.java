package com.mongo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongo.dao.SystemCodeDao;
import com.mongo.entity.Page;
import com.mongo.entity.SystemCode;

@Repository(value="systemCodeDao")
public class SystemCodeDaoImpl implements SystemCodeDao{

	@Autowired
	private BaseDao baseDao;

	
	@Override
	public SystemCode find(String id) {
		return baseDao.findOne(SystemCode.class, id);
	}

	@Override
	public Page findPage(Page page, SystemCode t) {
		Query query = new Query();
		return baseDao.findPage(SystemCode.class, page, query);
	}

	@Override
	public boolean remove(String id) {
		baseDao.removeOne(SystemCode.class, id);
		return true;
	}

	@Override
	public SystemCode save(SystemCode t) {
		return (SystemCode)baseDao.save(t);
	}

	@Override
	public SystemCode update(SystemCode t) {
		baseDao.findAndModify(t.getId(), t);
		return t;
	}

	@Override
	public List<SystemCode> findAll() {
		return baseDao.find(SystemCode.class);
	}

	
	
}
