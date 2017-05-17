package com.cidic.design.model;
// Generated 2017-5-9 16:09:09 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Judge generated by hbm2java
 */
@Entity
@Table(name = "judge", catalog = "design_competition")
public class Judge implements java.io.Serializable {

	private Integer id;
	private String name;
	private String headicon;
	private String subTitle;
	private byte category;
	private String description;
	private Date createtime;

	public Judge() {
	}

	public Judge(String name, String headicon, String subTitle, byte category) {
		this.name = name;
		this.headicon = headicon;
		this.subTitle = subTitle;
		this.category = category;
	}

	public Judge(String name, String headicon, String subTitle, byte category, String description, Date createtime) {
		this.name = name;
		this.headicon = headicon;
		this.subTitle = subTitle;
		this.category = category;
		this.description = description;
		this.createtime = createtime;
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

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "headicon", nullable = false, length = 30)
	public String getHeadicon() {
		return this.headicon;
	}

	public void setHeadicon(String headicon) {
		this.headicon = headicon;
	}

	@Column(name = "sub_title", nullable = false, length = 60)
	public String getSubTitle() {
		return this.subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	@Column(name = "category", nullable = false)
	public byte getCategory() {
		return this.category;
	}

	public void setCategory(byte category) {
		this.category = category;
	}

	@Column(name = "description", length = 600)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
