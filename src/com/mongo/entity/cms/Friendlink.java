package com.mongo.entity.cms;


/**
 * Friendlink
 * 
 * @author yangxing
 * 
 */
public class Friendlink implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 已审核
	 */
	public static final int AUDITED = 0;
	/**
	 * 未审核
	 */
	public static final int SAVED = 1;
	
	public void applyDefaultValue() {
		if (getSeq() == null) {
			setSeq(Integer.MAX_VALUE);
		}
		if(getStatus()==null){
			setStatus(AUDITED);
		}
	}
	
	private String id;
	
	private String name;
	private String url;
	private Integer seq;
	private String logo;
	private String description;
	private String email;
	private Boolean recommend;
	private Integer status;
	private Boolean withLogo;

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getRecommend() {
		return this.recommend;
	}

	public void setRecommend(Boolean recommend) {
		this.recommend = recommend;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Boolean getWithLogo() {
		return withLogo;
	}

	public void setWithLogo(Boolean withLogo) {
		this.withLogo = withLogo;
	}

}
