package com.mongo.entity;

import java.util.Date;

/**
 * Log entity. @author MyEclipse Persistence Tools
 */
public class Log implements java.io.Serializable
{
	private static final long serialVersionUID = 1309582605928485828L;
	private String logId;
	private String userId;
	private String name;
	private Date logDate;
	private Integer type;
	private String mac;
	private String ip;
	private Integer objectType;
	private String objectId;
	private String eventName;
	private String eventRecord;

	// Constructors

	/** default constructor */
	public Log()
	{
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getObjectType() {
		return objectType;
	}

	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventRecord() {
		return eventRecord;
	}

	public void setEventRecord(String eventRecord) {
		this.eventRecord = eventRecord;
	}
	

}