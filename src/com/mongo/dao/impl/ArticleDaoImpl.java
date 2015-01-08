package com.mongo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongo.dao.ArticleDao;
import com.mongo.entity.Page;
import com.mongo.entity.cms.Article;

@Repository(value="articleDao")
public class ArticleDaoImpl implements ArticleDao{

	@Autowired
	private BaseDao baseDao;

	

	@Override
	public Article find(String id) {
		return baseDao.findOne(Article.class, id);
	}

	@Override
	public Page findPage(Page page, Article t) {
		Query query = new Query();
		return baseDao.findPage(Article.class, page, query);
	}

	@Override
	public boolean remove(String id) {
		baseDao.removeOne(Article.class, id);
		return true;
	}

	@Override
	public Article save(Article t) {
		return (Article)baseDao.save(t);
	}

	@Override
	public Article update(Article t) {
		baseDao.findAndModify(t.getId(), t);
		return t;
	}

	
	
}
