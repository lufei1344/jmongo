package com.mongo.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */
public class Role implements java.io.Serializable
{
	private static final long serialVersionUID = -8220535212044563981L;
	private String id;
	private String name;
	private String description;
	private String status;
	private Date created;
	private Date lastmod;
	private Integer sort;
	private Integer creater;
	private Integer modifyer;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);
	private String[] menuIds;

	// Constructors

	/** default constructor */
	public Role()
	{
	}

	
	

	public String getName()
	{
		return this.name;
	}

	public void setName(String name )
	{
		this.name = name;
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

	public Integer getSort()
	{
		return this.sort;
	}

	public void setSort(Integer sort )
	{
		this.sort = sort;
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

	public Set<UserRole> getUserRoles()
	{
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles )
	{
		this.userRoles = userRoles;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}




	public String[] getMenuIds() {
		return menuIds;
	}




	public void setMenuIds(String[] menuIds) {
		this.menuIds = menuIds;
	}
	

}