package com.mongo.entity;

import java.util.Date;

/**
 * Organization entity. @author MyEclipse Persistence Tools
 */
public class Organization implements java.io.Serializable
{
	private static final long serialVersionUID = -3560252981789957584L;
	private String id;
	private Integer companyId;
	private String myid;
	private String pid;
	private String fullName;
	private String ename;
	private Integer manager;
	private String iconCls;
	private Integer assistantManager;
	private Integer empQty;
	private String status;
	private Date created;
	private Date lastmod;
	private String shortName;
	private String tel;
	private String fax;
	private String description;
	private String creater;
	private String modifyer;
	private String state="closed";

	// Constructors

	/** default constructor */
	public Organization()
	{
	}



	public Integer getCompanyId()
	{
		return this.companyId;
	}

	public void setCompanyId(Integer companyId )
	{
		this.companyId = companyId;
	}

	public String getMyid()
	{
		return this.myid;
	}

	public void setMyid(String myid )
	{
		this.myid = myid;
	}

	

	public String getPid() {
		return pid;
	}



	public void setPid(String pid) {
		this.pid = pid;
	}



	public String getFullName()
	{
		return this.fullName;
	}

	public void setFullName(String fullName )
	{
		this.fullName = fullName;
	}

	public String getEname()
	{
		return this.ename;
	}

	public void setEname(String ename )
	{
		this.ename = ename;
	}

	public Integer getManager()
	{
		return this.manager;
	}

	public void setManager(Integer manager )
	{
		this.manager = manager;
	}

	public String getIconCls()
	{
		return iconCls;
	}

	public void setIconCls(String iconCls )
	{
		this.iconCls = iconCls;
	}

	public Integer getAssistantManager()
	{
		return this.assistantManager;
	}

	public void setAssistantManager(Integer assistantManager )
	{
		this.assistantManager = assistantManager;
	}

	public Integer getEmpQty()
	{
		return this.empQty;
	}

	public void setEmpQty(Integer empQty )
	{
		this.empQty = empQty;
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

	public String getShortName()
	{
		return this.shortName;
	}

	public void setShortName(String shortName )
	{
		this.shortName = shortName;
	}

	public String getTel()
	{
		return this.tel;
	}

	public void setTel(String tel )
	{
		this.tel = tel;
	}

	public String getFax()
	{
		return this.fax;
	}

	public void setFax(String fax )
	{
		this.fax = fax;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description )
	{
		this.description = description;
	}


	public String getState()
	{
		return this.state;
	}

	public void setState(String state )
	{
		this.state = state;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getCreater() {
		return creater;
	}



	public void setCreater(String creater) {
		this.creater = creater;
	}



	public String getModifyer() {
		return modifyer;
	}



	public void setModifyer(String modifyer) {
		this.modifyer = modifyer;
	}
	
}