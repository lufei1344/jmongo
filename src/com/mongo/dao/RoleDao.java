package com.mongo.dao;

import java.util.List;

import com.mongo.entity.Menu;
import com.mongo.entity.Role;

public interface RoleDao extends ComDao<Role>{
	
	
	public List<Role> findAll();
	
	public boolean saveMenu(String roleId,String ids);
	
	public List<Menu> roleMenu(String roleId);
	
}
