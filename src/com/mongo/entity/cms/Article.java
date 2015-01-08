package com.mongo.entity.cms;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.mongo.entity.User;


public class Article implements Serializable {
	private static final long serialVersionUID = 4338391967508386112L;
	private String id;// 文章id
	private String title; // 文章标题
	private String titleImg; // 文章标题缩略图
	private String contentImg; // 文章内容缩略图
	private String author; // 作者
	private String origin; // 来源
	private String tags; // Tag标签
	private String summary; // 文章摘要
	private String content; // 文章内容
	private Date sysDate = new Date(); //（系统日期）
	private String releaseDate; // 发表日期（可以人为设置）
	private Date checkDate; // 审核通过日期
	private Date disableDate; // 禁用日期
	private Integer visitTotal=0; // 总共访问次数
	private Integer recommendLevel; // 推荐级别
	private Integer commentCount=0; // 评论数量
	private String relatedID; // 相关文章ID
	private Boolean commentState ; // 是否允许评论
	private String checkState; //状态
	private Channel channel; // 栏目ID
	private User user; // 发布者
	private Set<Comment> comments = new HashSet<Comment>();// 评论集合
	
	
	public Article() {
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleImg() {
		return titleImg;
	}
	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}
	public String getContentImg() {
		return contentImg;
	}
	public void setContentImg(String contentImg) {
		this.contentImg = contentImg;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSysDate() {
		return sysDate;
	}
	public void setSysDate(Date sysDate) {
		this.sysDate = sysDate;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Date getDisableDate() {
		return disableDate;
	}
	public void setDisableDate(Date disableDate) {
		this.disableDate = disableDate;
	}

	public Integer getVisitTotal() {
		return visitTotal;
	}
	public void setVisitTotal(Integer visitTotal) {
		this.visitTotal = visitTotal;
	}
	public Integer getRecommendLevel() {
		return recommendLevel;
	}
	public void setRecommendLevel(Integer recommendLevel) {
		this.recommendLevel = recommendLevel;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public String getRelatedID() {
		return relatedID;
	}
	public void setRelatedID(String relatedID) {
		this.relatedID = relatedID;
	}
	public Boolean getCommentState() {
		return commentState;
	}
	public void setCommentState(Boolean commentState) {
		this.commentState = commentState;
	}

	
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	
	public String getCheckState() {
		return checkState;
	}
	public void setCheckState(String checkState) {
		this.checkState = checkState;
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
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
