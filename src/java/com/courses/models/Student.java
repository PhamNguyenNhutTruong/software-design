package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s"),
	@NamedQuery(name="Student.findStudentByPerson", query="SELECT s FROM Student s WHERE s.person = :person"),
	@NamedQuery(name="Student.findStudentByGroup", query="SELECT s FROM Student s WHERE s.groupstudent = :group"),
	@NamedQuery(name="Student.checkStudentAndGroup", query="SELECT s FROM Student s WHERE s.studentId = :studentId AND s.groupstudent.groupId IS NULL"),
	@NamedQuery(name="Student.getStudentTheSameGroupByGroupId",query="SELECT s FROM Student s WHERE s.groupstudent.groupId = :groupId"),
	@NamedQuery(name="Student.getStudentByPersonId", query="SELECT s FROM Student s WHERE s.person.personId = :personId")
})
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="student_id")
	private String studentId;

	@Column(name="school_year")
	private String schoolYear;

	//bi-directional many-to-one association to JoinGroup
	@OneToMany(mappedBy="student")
	private List<JoinGroup> joingroups;

	//bi-directional many-to-one association to GroupStudent
	@ManyToOne
	@JoinColumn(name="group_id")
	private GroupStudent groupstudent;

	//bi-directional many-to-one association to Major
	@ManyToOne
	@JoinColumn(name="major_id")
	private Major major;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;

	public Student() {
	}

	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getSchoolYear() {
		return this.schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public List<JoinGroup> getJoingroups() {
		return this.joingroups;
	}

	public void setJoingroups(List<JoinGroup> joingroups) {
		this.joingroups = joingroups;
	}

	public JoinGroup addJoingroup(JoinGroup joingroup) {
		getJoingroups().add(joingroup);
		joingroup.setStudent(this);

		return joingroup;
	}

	public JoinGroup removeJoingroup(JoinGroup joingroup) {
		getJoingroups().remove(joingroup);
		joingroup.setStudent(null);

		return joingroup;
	}

	public GroupStudent getGroupstudent() {
		return this.groupstudent;
	}

	public void setGroupstudent(GroupStudent groupstudent) {
		this.groupstudent = groupstudent;
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

}