package com.mongo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongo.dao.RoleDao;
import com.mongo.entity.Menu;
import com.mongo.entity.Page;
import com.mongo.entity.Role;

@Repository(value="roleDao")
public class RoleDaoImpl implements RoleDao{

	@Autowired
	private BaseDao baseDao;

	

	@Override
	public Role find(String id) {
		return baseDao.findOne(Role.class, id);
	}

	@Override
	public Page findPage(Page page, Role t) {
		Query query = new Query();
		return baseDao.findPage(Role.class, page, query);
	}

	@Override
	public boolean remove(String id) {
		baseDao.removeOne(Role.class, id);
		return true;
	}

	@Override
	public Role save(Role t) {
		return (Role)baseDao.save(t);
	}

	@Override
	public Role update(Role t) {
		baseDao.findAndModify(t.getId(), t);
		return t;
	}

	@Override
	public List<Role> findAll() {
		return baseDao.find(Role.class);
	}

	@Override
	public boolean saveMenu(String roleid, String ids) {
		String[] id = ids.split(",");
		Role r = new Role();
		r.setId(roleid);
		r.setMenuIds(id);
		baseDao.findAndModify(roleid, r);
		return true;
	}

	@Override
	public List<Menu> roleMenu(String roleId) {
		Role r = baseDao.findOne(Role.class, roleId);
		return baseDao.find(Menu.class, new Query(Criteria.where("id").in(r.getMenuIds())));
	}
	
	
	
}
