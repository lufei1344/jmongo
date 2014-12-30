package com.mongo.entity;

import java.util.Date;

/**
 * SystemCode entity. @author MyEclipse Persistence Tools
 */
public class SystemCode implements java.io.Serializable
{
	private static final long serialVersionUID = -5522136453981132093L;
	private String id;
	private String codeMyid;
	private String name;
	private Integer sort;
	private String iconCls;
	private String state;
	private String type;
	private String pid;
	private Integer permissionId;
	private String description;
	private String status;
	private Date created;
	private Date lastmod;
	private Integer creater;
	private Integer modifyer;

	// Constructors

	/** default constructor */
	public SystemCode()
	{
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




	public String getCodeMyid()
	{
		return this.codeMyid;
	}

	public void setCodeMyid(String codeMyid )
	{
		this.codeMyid = codeMyid;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name )
	{
		this.name = name;
	}

	public Integer getSort()
	{
		return this.sort;
	}

	public void setSort(Integer sort )
	{
		this.sort = sort;
	}

	public Integer getPermissionId()
	{
		return permissionId;
	}

	public void setPermissionId(Integer permissionId )
	{
		this.permissionId = permissionId;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description )
	{
		this.description = description;
	}

	public String getStatus()
	{
		return this.status;
	}

	public void setStatus(String status )
	{
		this.status = status;
	}
	
	public String getType()
	{
		return type;
	}

	public void setType(String type )
	{
		this.type = type;
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
}