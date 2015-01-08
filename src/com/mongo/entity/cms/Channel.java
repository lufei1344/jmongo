package com.mongo.entity.cms;

import java.io.Serializable;
import java.util.Date;


public class Channel implements Serializable {
	private static final long serialVersionUID = 5257758268484027105L;
	private String id; // id
	private String pid;
	private String name; // 栏目名
	private String info; // 栏目说明/内容
	private Integer rankid = 0; // 排序id
	private Boolean single = false;// 是否是单页
	private Boolean display = false;// 是否在导航栏显示
	private Boolean displayInIndex = false;// 是否在首页显示
	private Integer visitTotal = 0; // 总共访问次数
	

	private Date sysDate = new Date(); //（系统日期）
	private String checkState; //状态

	public Channel() {
	}

	
	public Boolean getSingle() {
		return single;
	}

	public Integer getVisitTotal() {
		return visitTotal;
	}

	public void setVisitTotal(Integer visitTotal) {
		this.visitTotal = visitTotal;
	}

	public void setSingle(Boolean single) {
		this.single = single;
	}

	public Boolean getDisplay() {
		return display;
	}

	public Boolean getDisplayInIndex() {
		return displayInIndex;
	}

	public void setDisplayInIndex(Boolean displayInIndex) {
		this.displayInIndex = displayInIndex;
	}

	public void setDisplay(Boolean display) {
		this.display = display;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getRankid() {
		return rankid;
	}

	public void setRankid(Integer rankid) {
		this.rankid = rankid;
	}


	
	public Date getSysDate() {
		return sysDate;
	}

	public void setSysDate(Date sysDate) {
		this.sysDate = sysDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Channel other = (Channel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	
}
