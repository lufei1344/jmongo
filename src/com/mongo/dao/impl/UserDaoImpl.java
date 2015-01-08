package com.mongo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongo.dao.UserDao;
import com.mongo.entity.Page;
import com.mongo.entity.User;

@Repository(value="userDao")
public class UserDaoImpl implements UserDao{

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public User validate(User user) {
		return baseDao.getMongoTemplate().findOne(new Query(Criteria.where("account").is(user.getAccount()).and("password").is(user.getPassword())), User.class);
	}
	@Override
	public Page findPage(Page page, User user) {
		Query query = new Query();
		if(user != null){
			Criteria c =Criteria.where("id").ne("");
			if(user.getAccount() != null){
				c.and("account").regex(user.getAccount());
			}
			if(user.getEmail() != null){
				c.and("email").regex(user.getEmail());
			}
			if(user.getName() != null){
				c.and("name").regex(user.getName());
			}
			if(user.getDescription() != null){
				c.and("description").regex(user.getDescription());
			}
			query.addCriteria(c);
		}
		return baseDao.findPage(User.class, page, query);
	}
	@Override
	public boolean remove(String id) {
		baseDao.removeOne(User.class, id);
		return true;
	}
	@Override
	public User save(User user) {
		return (User)baseDao.save(user);
	}
	@Override
	public User find(String id) {
		return baseDao.findOne(User.class, id);
	}
	@Override
	public User update(User user) {
		baseDao.findAndModify(user.getId(), user);
		return user;
	}
	
}
