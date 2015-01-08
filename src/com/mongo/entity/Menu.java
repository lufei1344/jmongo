package com.mongo.entity;

import java.util.Date;
import java.util.List;

/**
 * Permission entity. @author MyEclipse Persistence Tools
 */
public class Menu implements java.io.Serializable
{
	private static final long serialVersionUID = 1136795483472903508L;
	private String id;
	private String pid;
	private String name;
	private String pname;
	private Integer sort;
	private String myid;
	private String type;
	private String state;
	private String isused;
	private String url;
	private String iconCls;
	private String status;
	private String description;
	private Date created;
	private Date lastmod;
	private Integer creater;
	private Integer modifyer;
	private List<Menu> child;

	// Constructors

	/** default constructor */
	public Menu()
	{
	}

	/** minimal constructor */
	public Menu(String status, Date created)
	{
		this.status = status;
		this.created = created;
	}

	
	// Property accessors
	

	

	public String getName()
	{
		return this.name;
	}

	public void setName(String name )
	{
		this.name = name;
	}

	public String getPname()
	{
		return this.pname;
	}

	public void setPname(String pname )
	{
		this.pname = pname;
	}

	public Integer getSort()
	{
		return this.sort;
	}

	public void setSort(Integer sort )
	{
		this.sort = sort;
	}

	public String getMyid()
	{
		return this.myid;
	}

	public void setMyid(String myid )
	{
		this.myid = myid;
	}

	public String getType()
	{
		return this.type;
	}

	public void setType(String type )
	{
		this.type = type;
	}

	public String getIsused()
	{
		return this.isused;
	}

	public void setIsused(String isused )
	{
		this.isused = isused;
	}

	public String getUrl()
	{
		return this.url;
	}

	public void setUrl(String url )
	{
		this.url = url;
	}
	public String getState()
	{
		return this.state;
	}

	public void setState(String state )
	{
		this.state = state;
	}
	
	public String getIconCls()
	{
		return iconCls;
	}

	public void setIconCls(String iconCls )
	{
		this.iconCls = iconCls;
	}
	public String getStatus()
	{
		return this.status;
	}


	public void setStatus(String status )
	{
		this.status = status;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description )
	{
		this.description = description;
	}

	public Date getCreated()
	{
		return this.created;
	}

	public void setCreated(Date created )
	{
		this.created = created;
	}

	public Date getLastmod()
	{
		return this.lastmod;
	}

	public void setLastmod(Date lastmod )
	{
		this.lastmod = lastmod;
	}

	public Integer getCreater()
	{
		return this.creater;
	}

	public void setCreater(Integer creater )
	{
		this.creater = creater;
	}

	public Integer getModifyer()
	{
		return this.modifyer;
	}

	public void setModifyer(Integer modifyer )
	{
		this.modifyer = modifyer;
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

	public List<Menu> getChild() {
		return child;
	}

	public void setChild(List<Menu> child) {
		this.child = child;
	}

}