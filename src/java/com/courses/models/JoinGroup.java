package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the joingroup database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="JoinGroup.findAll", query="SELECT j FROM JoinGroup j"),
	@NamedQuery(name="JoinGroup.getRequestJoinGroup", query="SELECT j FROM JoinGroup j WHERE j.groupstudent.groupId = :groupId"),
	@NamedQuery(name="JoinGroup.getJoinGroupByStudentId", query="SELECT j FROM JoinGroup j WHERE j.student.studentId = :studentId"),
})
public class JoinGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private JoinGroupPK id;

	private byte status;

	//bi-directional many-to-one association to GroupStudent
	@ManyToOne
	@JoinColumn(name="group_id", nullable=false, insertable=false, updatable=false)
	private GroupStudent groupstudent;

	//bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name="student_id", nullable=false, insertable=false, updatable=false)
	private Student student;

	public JoinGroup() {
	}

	public JoinGroupPK getId() {
		return this.id;
	}

	public void setId(JoinGroupPK id) {
		this.id = id;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public GroupStudent getGroupstudent() {
		return this.groupstudent;
	}

	public void setGroupstudent(GroupStudent groupstudent) {
		this.groupstudent = groupstudent;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}