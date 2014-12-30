package com.mongo.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
public class User implements java.io.Serializable
{
	private static final long serialVersionUID = 3091722681204768199L;
	private String id;
	private String myid;
	private String account;
	private String name;
	private Integer organizeId;
	private String organizeName;
	private Integer dutyId;
	private Integer titleId;
	private String password;
	private String email;
	private String lang;
	private String theme;
	private Date firstVisit;
	private Date previousVisit;
	private Date lastVisits;
	private Integer loginCount;
	private Integer isemployee;
	private String status;
	private String ip;
	private String description;
	private Integer questionId;
	private String answer;
	private Integer isonline;
	private Date created;
	private Date lastmod;
	private Integer creater;
	private Integer modifyer;
	private String tel;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	// Constructors

	/** default constructor */
	public User()
	{
	}

	/** full constructor */
	public User(String myid, String account, String name, Integer organizeId, String organizeName,
			Integer dutyId, Integer titleId, String password, String email, String lang,
			String theme, Date firstVisit, Date previousVisit, Date lastVisits, Integer loginCount,
			Integer isemployee, String status, String ip, String description, Integer questionId,
			String answer, Integer isonline, Date created, Date lastmod, Integer creater,
			Integer modifyer, String tel, Set<UserRole> userRoles)
	{
		this.myid = myid;
		this.account = account;
		this.name = name;
		this.organizeId = organizeId;
		this.organizeName = organizeName;
		this.dutyId = dutyId;
		this.titleId = titleId;
		this.password = password;
		this.email = email;
		this.lang = lang;
		this.theme = theme;
		this.firstVisit = firstVisit;
		this.previousVisit = previousVisit;
		this.lastVisits = lastVisits;
		this.loginCount = loginCount;
		this.isemployee = isemployee;
		this.status = status;
		this.ip = ip;
		this.description = description;
		this.questionId = questionId;
		this.answer = answer;
		this.isonline = isonline;
		this.created = created;
		this.lastmod = lastmod;
		this.creater = creater;
		this.modifyer = modifyer;
		this.tel = tel;
		this.userRoles = userRoles;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMyid()
	{
		return this.myid;
	}

	public void setMyid(String myid )
	{
		this.myid = myid;
	}

	public String getAccount()
	{
		return this.account;
	}

	public void setAccount(String account )
	{
		this.account = account;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name )
	{
		this.name = name;
	}

	public Integer getOrganizeId()
	{
		return this.organizeId;
	}

	public void setOrganizeId(Integer organizeId )
	{
		this.organizeId = organizeId;
	}

	public String getOrganizeName()
	{
		return this.organizeName;
	}

	public void setOrganizeName(String organizeName )
	{
		this.organizeName = organizeName;
	}

	public Integer getDutyId()
	{
		return this.dutyId;
	}

	public void setDutyId(Integer dutyId )
	{
		this.dutyId = dutyId;
	}

	public Integer getTitleId()
	{
		return this.titleId;
	}

	public void setTitleId(Integer titleId )
	{
		this.titleId = titleId;
	}

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password )
	{
		this.password = password;
	}

	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email )
	{
		this.email = email;
	}

	public String getLang()
	{
		return this.lang;
	}

	public void setLang(String lang )
	{
		this.lang = lang;
	}

	public String getTheme()
	{
		return this.theme;
	}

	public void setTheme(String theme )
	{
		this.theme = theme;
	}

	public Date getFirstVisit()
	{
		return this.firstVisit;
	}

	public void setFirstVisit(Date firstVisit )
	{
		this.firstVisit = firstVisit;
	}

	public Date getPreviousVisit()
	{
		return this.previousVisit;
	}

	public void setPreviousVisit(Date previousVisit )
	{
		this.previousVisit = previousVisit;
	}

	public Date getLastVisits()
	{
		return this.lastVisits;
	}

	public void setLastVisits(Date lastVisits )
	{
		this.lastVisits = lastVisits;
	}

	public Integer getLoginCount()
	{
		return this.loginCount;
	}

	public void setLoginCount(Integer loginCount )
	{
		this.loginCount = loginCount;
	}

	public Integer getIsemployee()
	{
		return this.isemployee;
	}

	public void setIsemployee(Integer isemployee )
	{
		this.isemployee = isemployee;
	}

	public String getStatus()
	{
		return this.status;
	}

	public void setStatus(String status )
	{
		this.status = status;
	}

	public String getIp()
	{
		return this.ip;
	}

	public void setIp(String ip )
	{
		this.ip = ip;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description )
	{
		this.description = description;
	}

	public Integer getQuestionId()
	{
		return this.questionId;
	}

	public void setQuestionId(Integer questionId )
	{
		this.questionId = questionId;
	}

	public String getAnswer()
	{
		return this.answer;
	}

	public void setAnswer(String answer )
	{
		this.answer = answer;
	}

	public Integer getIsonline()
	{
		return this.isonline;
	}

	public void setIsonline(Integer isonline )
	{
		this.isonline = isonline;
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

	public String getTel()
	{
		return this.tel;
	}

	public void setTel(String tel )
	{
		this.tel = tel;
	}

	public Set<UserRole> getUserRoles()
	{
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles )
	{
		this.userRoles = userRoles;
	}

}