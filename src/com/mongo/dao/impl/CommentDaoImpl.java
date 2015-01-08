package com.mongo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongo.dao.CommentDao;
import com.mongo.entity.Page;
import com.mongo.entity.cms.Comment;

@Repository(value="commentDao")
public class CommentDaoImpl implements CommentDao{

	@Autowired
	private BaseDao baseDao;

	

	@Override
	public Comment find(String id) {
		return baseDao.findOne(Comment.class, id);
	}

	@Override
	public Page findPage(Page page, Comment t) {
		Query query = new Query();
		return baseDao.findPage(Comment.class, page, query);
	}

	@Override
	public boolean remove(String id) {
		baseDao.removeOne(Comment.class, id);
		return true;
	}

	@Override
	public Comment save(Comment t) {
		return (Comment)baseDao.save(t);
	}

	@Override
	public Comment update(Comment t) {
		baseDao.findAndModify(t.getId(), t);
		return t;
	}

	
	
}
