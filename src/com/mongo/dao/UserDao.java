package com.mongo.dao;

import com.mongo.entity.Page;
import com.mongo.entity.User;

public interface UserDao {
	
	/**
	 * 校验登陆
	 * @param user
	 * @return
	 */
	public User validate(User user);
	
	public User find(String id);
	
	public User update(User user);
	
	public Page findPage(Page page,User user);
	
	public User save(User user);
	
	public boolean remove(String id);
}
