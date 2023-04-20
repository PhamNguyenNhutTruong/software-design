package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the groupstudent database table. 
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="GroupStudent.findAll", query="SELECT g FROM GroupStudent g"),
	@NamedQuery(name="GroupStudent.checkLeader", query="SELECT g FROM GroupStudent g WHERE g.leaderId = :leaderId AND g.topic IS NULL"),
	@NamedQuery(name="GroupStudent.checkRole", query="SELECT g FROM GroupStudent g WHERE g.leaderId = :leaderId"),
	@NamedQuery(name="GroupStudent.getGroupStudentByTopic", query="SELECT g FROM GroupStudent g WHERE g.topic = :topic"),
	@NamedQuery(name="GroupStudent.getGroupStudent", query="SELECT g FROM GroupStudent g WHERE g.leaderId IS NOT NULL AND g.isDeleted = :isDeleted"),
	@NamedQuery(name="GroupStudent.findByBoard", query="SELECT g FROM GroupStudent g WHERE g.board = :board"),
	})
public class GroupStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="group_id")
	private String groupId;

	@Column(name="current_number")
	private int currentNumber;

	private String description;

	@Column(name="is_deleted")
	private byte isDeleted;

	@Column(name="is_full")
	private byte isFull;

	@Column(name="leader_id")
	private String leaderId;

	//bi-directional many-to-one association to Board
	@ManyToOne
	@JoinColumn(name="board_id")
	private Board board;

	//bi-directional many-to-one association to Topic
	@ManyToOne
	@JoinColumn(name="topic_id")
	private Topic topic;

	//bi-directional many-to-one association to JoinGroup
	@OneToMany(mappedBy="groupstudent")
	private List<JoinGroup> joingroups;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="groupstudent")
	private List<Student> students;

	public GroupStudent() {
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public int getCurrentNumber() {
		return this.currentNumber;
	}

	public void setCurrentNumber(int currentNumber) {
		this.currentNumber = currentNumber;
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

	public String getLeaderId() {
		return this.leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public Board getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public List<JoinGroup> getJoingroups() {
		return this.joingroups;
	}

	public void setJoingroups(List<JoinGroup> joingroups) {
		this.joingroups = joingroups;
	}

	public JoinGroup addJoingroup(JoinGroup joingroup) {
		getJoingroups().add(joingroup);
		joingroup.setGroupstudent(this);

		return joingroup;
	}

	public JoinGroup removeJoingroup(JoinGroup joingroup) {
		getJoingroups().remove(joingroup);
		joingroup.setGroupstudent(null);

		return joingroup;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setGroupstudent(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setGroupstudent(null);

		return student;
	}

}