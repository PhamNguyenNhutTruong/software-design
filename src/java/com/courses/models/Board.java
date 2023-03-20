package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the board database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Board.findAll", query = "SELECT b FROM Board b"),
	@NamedQuery(name = "Board.count", query = "SELECT COUNT(b) FROM Board b"),
	@NamedQuery(name = "Board.getListBoardWithCondiotionDeleted", query = "SELECT b FROM Board b WHERE b.isDeleted = :isDeleted")
})
public class Board implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="board_id")
	private String boardId;

	@Column(name="board_name")
	private String boardName;

	private String description;

	@Column(name="is_deleted")
	private byte isDeleted;

	@Column(name="no_member")
	private int noMember;

	//bi-directional many-to-one association to GroupStudent
	@OneToMany(mappedBy="board")
	private List<GroupStudent> groupstudents;

	//bi-directional many-to-one association to TeacherBoard
	@OneToMany(mappedBy="board")
	private List<TeacherBoard> teacherboards;

	public Board() {
	}

	public String getBoardId() {
		return this.boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getBoardName() {
		return this.boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
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

	public int getNoMember() {
		return this.noMember;
	}

	public void setNoMember(int noMember) {
		this.noMember = noMember;
	}

	public List<GroupStudent> getGroupstudents() {
		return this.groupstudents;
	}

	public void setGroupstudents(List<GroupStudent> groupstudents) {
		this.groupstudents = groupstudents;
	}

	public GroupStudent addGroupstudent(GroupStudent groupstudent) {
		getGroupstudents().add(groupstudent);
		groupstudent.setBoard(this);

		return groupstudent;
	}

	public GroupStudent removeGroupstudent(GroupStudent groupstudent) {
		getGroupstudents().remove(groupstudent);
		groupstudent.setBoard(null);

		return groupstudent;
	}

	public List<TeacherBoard> getTeacherboards() {
		return this.teacherboards;
	}

	public void setTeacherboards(List<TeacherBoard> teacherboards) {
		this.teacherboards = teacherboards;
	}

	public TeacherBoard addTeacherboard(TeacherBoard teacherboard) {
		getTeacherboards().add(teacherboard);
		teacherboard.setBoard(this);

		return teacherboard;
	}

	public TeacherBoard removeTeacherboard(TeacherBoard teacherboard) {
		getTeacherboards().remove(teacherboard);
		teacherboard.setBoard(null);

		return teacherboard;
	}

}