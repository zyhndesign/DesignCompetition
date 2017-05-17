package com.cidic.design.model;
// Generated 2017-5-9 16:09:09 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Production generated by hbm2java
 */
@Entity
@Table(name = "production", catalog = "design_competition")
public class Production implements java.io.Serializable {

	private Integer id;
	private String title;
	private int groupId;
	private int userId;
	private String content;
	private String attachFile;
	private Date createTime;
	private String thumb;
	private Float score;
	private String pimage;
	
	public Production() {
	}

	public Production(String title, int groupId, int userId, String content, Date createTime) {
		this.title = title;
		this.groupId = groupId;
		this.userId = userId;
		this.content = content;
		this.createTime = createTime;
	}

	public Production(String title, int groupId, int userId, String thumb, String pimage, String content,
			String attachFile, Float score, Date createTime) {
		this.title = title;
		this.groupId = groupId;
		this.userId = userId;
		this.thumb = thumb;
		this.pimage = pimage;
		this.content = content;
		this.attachFile = attachFile;
		this.score = score;
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "title", nullable = false, length = 30)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "groupId", nullable = false)
	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Column(name = "userId", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "thumb", nullable = false)
	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	@Column(name = "attach_file", length = 50)
	public String getAttachFile() {
		return this.attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

	@Column(name = "pImage")
	public String getPimage() {
		return this.pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "score", precision = 5)
	public Float getScore() {
		return this.score;
	}

	public void setScore(Float score) {
		this.score = score;
	}
}
