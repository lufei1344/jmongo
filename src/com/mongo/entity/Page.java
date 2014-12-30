package com.mongo.entity;

import java.util.List;

public class Page {
	
	private int page = 0;
	private int rows=15;
	private long count;
	private List list;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	
}
