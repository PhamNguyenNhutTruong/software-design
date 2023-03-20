package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the topic database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Topic.findAll", query="SELECT t FROM Topic t"),
	@NamedQuery(name="Topic.getTopicByConditionSelect", query="SELECT t FROM Topic t WHERE t.isSelected = :is_selected AND t.status = 1 AND t.isDeleted = 0"),
	@NamedQuery(name="Topic.findSelectedTopic", query="SELECT t FROM Topic t where t.isSelected = :isSelected"),
	@NamedQuery(name="Topic.findTopicByTeacher", query="SELECT t FROM Topic t where t.teacher = :teacher"),
	@NamedQuery(name="Topic.findTopicByTeacherAndStatus", query="SELECT t FROM Topic t where t.teacher = :teacher and t.status = 0"),
	@NamedQuery(name="Topic.findSpecifiedTopic", query="SELECT t FROM Topic t where t.teacher = :teacher and t.isSelected = :isSelected and t.status = 1"),
	@NamedQuery(name="Topic.findByStatusAndIsDeleted", query="SELECT t FROM Topic t WHERE t.status = :status AND t.isDeleted = :isDeleted")
})
public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="topic_id")
	private String topicId;

	private String description;

	@Column(name="is_deleted")
	private byte isDeleted;

	@Column(name="is_full")
	private byte isFull;

	@Column(name="is_selected")
	private byte isSelected;

	@Column(name="max_mo_member")
	private int maxMoMember;

	private byte status;

	@Column(name="topic_name")
	private String topicName;

	//bi-directional many-to-one association to GroupStudent
	@OneToMany(mappedBy="topic")
	private List<GroupStudent> groupstudents;

	//bi-directional many-to-one association to Major
	@ManyToOne
	@JoinColumn(name="major_id")
	private Major major;

	//bi-directional many-to-one association to RegistrationPeriod
	@ManyToOne
	@JoinColumn(name="registration_period_id")
	private RegistrationPeriod registrationperiod;

	//bi-directional many-to-one association to Teacher
	@ManyToOne
	@JoinColumn(name="teacher_id")
	private Teacher teacher;

	public Topic() {
	}

	public String getTopicId() {
		return this.topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public byte getIsFull() {
		return this.isFull;
	}

	public void setIsFull(byte isFull) {
		this.isFull = isFull;
	}

	public byte getIsSelected() {
		return this.isSelected;
	}

	public void setIsSelected(byte isSelected) {
		this.isSelected = isSelected;
	}

	public int getMaxMoMember() {
		return this.maxMoMember;
	}

	public void setMaxMoMember(int maxMoMember) {
		this.maxMoMember = maxMoMember;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getTopicName() {
		return this.topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public List<GroupStudent> getGroupstudents() {
		return this.groupstudents;
	}

	public void setGroupstudents(List<GroupStudent> groupstudents) {
		this.groupstudents = groupstudents;
	}

	public GroupStudent addGroupstudent(GroupStudent groupstudent) {
		getGroupstudents().add(groupstudent);
		groupstudent.setTopic(this);

		return groupstudent;
	}

	public GroupStudent removeGroupstudent(GroupStudent groupstudent) {
		getGroupstudents().remove(groupstudent);
		groupstudent.setTopic(null);

		return groupstudent;
	}

	public Major getMajor() {
		return this.major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public RegistrationPeriod getRegistrationperiod() {
		return this.registrationperiod;
	}

	public void setRegistrationperiod(RegistrationPeriod registrationperiod) {
		this.registrationperiod = registrationperiod;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}