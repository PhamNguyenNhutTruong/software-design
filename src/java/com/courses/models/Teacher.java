package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the teacher database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Teacher.findAll", query="SELECT t FROM Teacher t"),
	@NamedQuery(name="Teacher.findTeacherByPerson", query="SELECT t FROM Teacher t WHERE t.person = :person"),
	@NamedQuery(name="Teacher.findByPerson", query="SELECT t FROM Teacher t WHERE t.person = :person"),
})
public class Teacher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="teacher_id")
	private String teacherId;

	@Column(name="is_head")
	private byte isHead;

	//bi-directional many-to-one association to Major
	@ManyToOne
	@JoinColumn(name="major_id")
	private Major major;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;

	//bi-directional many-to-one association to TeacherBoard
	@OneToMany(mappedBy="teacher")
//	@OneToMany(fetch = FetchType.EAGER, mappedBy="teacher",cascade = CascadeType.ALL)
	private List<TeacherBoard> teacherboards;

	//bi-directional many-to-one association to Topic
	@OneToMany(mappedBy="teacher")
//	@OneToMany(fetch = FetchType.EAGER, mappedBy="teacher",cascade = CascadeType.ALL)
	private List<Topic> topics;

	public Teacher() {
	}

	public String getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public byte getIsHead() {
		return this.isHead;
	}

	public void setIsHead(byte isHead) {
		this.isHead = isHead;
	}

	public Major getMajor() {
		return this.major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<TeacherBoard> getTeacherboards() {
		return this.teacherboards;
	}

	public void setTeacherboards(List<TeacherBoard> teacherboards) {
		this.teacherboards = teacherboards;
	}

	public TeacherBoard addTeacherboard(TeacherBoard teacherboard) {
		getTeacherboards().add(teacherboard);
		teacherboard.setTeacher(this);

		return teacherboard;
	}

	public TeacherBoard removeTeacherboard(TeacherBoard teacherboard) {
		getTeacherboards().remove(teacherboard);
		teacherboard.setTeacher(null);

		return teacherboard;
	}

	public List<Topic> getTopics() {
		return this.topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public Topic addTopic(Topic topic) {
		getTopics().add(topic);
		topic.setTeacher(this);

		return topic;
	}

	public Topic removeTopic(Topic topic) {
		getTopics().remove(topic);
		topic.setTeacher(null);

		return topic;
	}

}