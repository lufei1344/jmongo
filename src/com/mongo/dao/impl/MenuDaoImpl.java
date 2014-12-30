package com.mongo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongo.dao.MenuDao;
import com.mongo.entity.Menu;
import com.mongo.entity.Page;

@Repository(value="menuDao")
public class MenuDaoImpl implements MenuDao{

	@Autowired
	private BaseDao baseDao;

	@Override
	public List<Menu> getUserMenu(String uid) {
		List<Menu> pmenu = baseDao.find(Menu.class, new Query(Criteria.where("pid").is(null)));
		for(Menu m : pmenu){
			m.setChild(baseDao.find(Menu.class,new Query(Criteria.where("pid").is(m.getId()))));
		}
		return pmenu;
	}

	@Override
	public Menu find(String id) {
		return baseDao.findOne(Menu.class, id);
	}

	@Override
	public Page findPage(Page page, Menu t) {
		Query query = new Query();
		return baseDao.findPage(Menu.class, page, query);
	}

	@Override
	public boolean remove(String id) {
		baseDao.removeOne(Menu.class, id);
		return true;
	}

	@Override
	public Menu save(Menu t) {
		return (Menu)baseDao.save(t);
	}

	@Override
	public Menu update(Menu t) {
		baseDao.findAndModify(t.getId(), t);
		return t;
	}

	@Override
	public List<Menu> findAll() {
		return baseDao.find(Menu.class);
	}
	
	
	
}
