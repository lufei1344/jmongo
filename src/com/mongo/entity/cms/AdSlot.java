package com.mongo.entity.cms;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

/**
 * 广告板位
 * 
 * @author liufang
 * 
 */
public class AdSlot implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	public static final int TEXT = 1;
	public static final int IMAGE = 2;
	public static final int FLASH = 3;
	public static final int CODE = 4;

	@Transient
	public void addAd(Ad ad) {
		List<Ad> ads = getAds();
		if (ads == null) {
			ads = new ArrayList<Ad>();
			setAds(ads);
		}
		ads.add(ad);
	}

	@Transient
	public void removeAd(Ad ad) {
		List<Ad> ads = getAds();
		if (ads != null) {
			ads.remove(ad);
		}

	}

	@Transient
	public void applyDefaultValue() {
		if (getType() == null) {
			setType(TEXT);
		}
	}

	private String id;
	private List<Ad> ads = new ArrayList<Ad>(0);


	private String name;
	private String number;
	private String description;
	private Integer type;
	private String template;
	private Integer width;
	private Integer height;

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Ad> getAds() {
		return this.ads;
	}

	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}

	

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Integer getWidth() {
		return this.width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
}
