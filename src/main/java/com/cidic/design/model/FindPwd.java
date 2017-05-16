package com.cidic.design.model;
// Generated 2017-5-16 12:23:53 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FindPwd generated by hbm2java
 */
@Entity
@Table(name = "find_pwd", catalog = "design_competition")
public class FindPwd implements java.io.Serializable {

	private Integer id;
	private String email;
	private String validCode;
	private Integer outDate;

	public FindPwd() {
	}

	public FindPwd(String email, String validCode) {
		this.email = email;
		this.validCode = validCode;
	}

	public FindPwd(String email, String validCode, Integer outDate) {
		this.email = email;
		this.validCode = validCode;
		this.outDate = outDate;
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

	@Column(name = "email", nullable = false, length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "validCode", nullable = false, length = 30)
	public String getValidCode() {
		return this.validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	@Column(name = "outDate")
	public Integer getOutDate() {
		return this.outDate;
	}

	public void setOutDate(Integer outDate) {
		this.outDate = outDate;
	}

}