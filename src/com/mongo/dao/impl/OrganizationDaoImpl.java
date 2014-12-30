package com.mongo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongo.dao.OrganizationDao;
import com.mongo.entity.Organization;
import com.mongo.entity.Page;

@Repository(value="organizationDao")
public class OrganizationDaoImpl implements OrganizationDao{

	@Autowired
	private BaseDao baseDao;

	
	@Override
	public Organization find(String id) {
		return baseDao.findOne(Organization.class, id);
	}

	@Override
	public Page findPage(Page page, Organization t) {
		Query query = new Query();
		if(t != null && t.getId() != null){
			Criteria c =Criteria.where("pid").is(t.getId());
			query.addCriteria(c);
		}else{
			Criteria c =Criteria.where("pid").is(null);
			query.addCriteria(c);
		}
		return baseDao.findPage(Organization.class, page, query);
	}

	@Override
	public boolean remove(String id) {
		baseDao.removeOne(Organization.class, id);
		return true;
	}

	@Override
	public Organization save(Organization t) {
		return (Organization)baseDao.save(t);
	}

	@Override
	public Organization update(Organization t) {
		baseDao.findAndModify(t.getId(), t);
		return t;
	}

	@Override
	public List<Organization> findAll() {
		return baseDao.find(Organization.class);
	}

	
	
}
