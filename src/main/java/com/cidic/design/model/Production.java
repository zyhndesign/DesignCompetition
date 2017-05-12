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
	private Date createtime;
	private String thumb;
	private Byte score;
	
	public Production() {
	}

	public Production(String title, int groupId, int userId, String content) {
		this.title = title;
		this.groupId = groupId;
		this.userId = userId;
		this.content = content;
	}

	public Production(String title, int groupId, int userId, String content, String attachFile) {
		this.title = title;
		this.groupId = groupId;
		this.userId = userId;
		this.content = content;
		this.attachFile = attachFile;
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

	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Column(name = "score")
	public Byte getScore() {
		return this.score;
	}

	public void setScore(Byte score) {
		this.score = score;
	}
}
