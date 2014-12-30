package com.mongo.dao;

import com.mongo.entity.Page;

public interface ComDao<T> {
	
	public T find(String id);
	
	public  T update(T t);
	
	public Page findPage(Page page,T t);
	
	public T save(T t);
	
	public boolean remove(String id);
}
