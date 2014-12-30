package com.mongo.dao;

import java.util.List;

import com.mongo.entity.Organization;

public interface OrganizationDao extends ComDao<Organization>{
	
	public List<Organization> findAll();
	
}
