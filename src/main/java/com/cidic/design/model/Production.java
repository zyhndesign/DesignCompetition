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
@Table(name = "production", catalog = "design_competition_seniorman")
public class Production implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private int groupId;
	private int userId;
	private String content;
	private String attachFile;
	private Date createTime;
	private String thumb;
	private Float score;
	private Byte status; //1.已提交、2.审核未通过、3.审核已通过、4.初选入围、5.初选未入围、6.复选入围、7复选未入围
	private String pimage;
	private byte category;  //参赛类别  1： 生活辅助类， 2：智能养老类， 3：综合设计类
	private byte participantType; //参赛人员类型 1：个人， 2：团队， 3：公司
	private String participantName; //参赛人员名称、团队名称、公司名称
	private String participantIdNumber; //参赛人员身份证号码、营业执照号码
	private String participantBrief; //简介
	private String teamMember; //团队成员
	private String weblink; //网页链接
	
	public Production() {
	}

	public Production(String title, int groupId, int userId, String content, byte category, byte participantType,
			String participantName, String participantIdNumber, String teamMember, Date createTime) {
		this.title = title;
		this.groupId = groupId;
		this.userId = userId;
		this.content = content;
		this.category = category;
		this.participantType = participantType;
		this.participantName = participantName;
		this.participantIdNumber = participantIdNumber;
		this.teamMember = teamMember;
		this.createTime = createTime;
	}

	public Production(String title, int groupId, int userId, String thumb, String pimage, String content,
			String attachFile, Byte status, byte category, byte participantType, String participantName,
			String participantIdNumber, String participantBrief, String teamMember, String weblink, Float score,
			Date createTime) {
		this.title = title;
		this.groupId = groupId;
		this.userId = userId;
		this.thumb = thumb;
		this.pimage = pimage;
		this.content = content;
		this.attachFile = attachFile;
		this.status = status;
		this.category = category;
		this.participantType = participantType;
		this.participantName = participantName;
		this.participantIdNumber = participantIdNumber;
		this.participantBrief = participantBrief;
		this.teamMember = teamMember;
		this.weblink = weblink;
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

	@Column(name = "attach_file", length = 100)
	public String getAttachFile() {
		return this.attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

	@Column(name = "status")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
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
	
	@Column(name = "category", nullable = false)
	public byte getCategory() {
		return this.category;
	}

	public void setCategory(byte category) {
		this.category = category;
	}

	@Column(name = "participant_type", nullable = false)
	public byte getParticipantType() {
		return this.participantType;
	}

	public void setParticipantType(byte participantType) {
		this.participantType = participantType;
	}

	@Column(name = "participant_name", nullable = false, length = 20)
	public String getParticipantName() {
		return this.participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	@Column(name = "participant_id_number", nullable = false, length = 25)
	public String getParticipantIdNumber() {
		return this.participantIdNumber;
	}

	public void setParticipantIdNumber(String participantIdNumber) {
		this.participantIdNumber = participantIdNumber;
	}

	@Column(name = "participant_brief")
	public String getParticipantBrief() {
		return this.participantBrief;
	}

	public void setParticipantBrief(String participantBrief) {
		this.participantBrief = participantBrief;
	}

	@Column(name = "team_member", length = 30)
	public String getTeamMember() {
		return this.teamMember;
	}

	public void setTeamMember(String teamMember) {
		this.teamMember = teamMember;
	}

	@Column(name = "weblink")
	public String getWeblink() {
		return this.weblink;
	}

	public void setWeblink(String weblink) {
		this.weblink = weblink;
	}
}
