package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries({ @NamedQuery(name = "TeacherBoard.findAll", query = "SELECT t FROM TeacherBoard t"),
		@NamedQuery(name = "TeacherBoard.findByBoard", query = "SELECT t FROM TeacherBoard t WHERE t.board = :board"),
		@NamedQuery(name = "TeacherBoard.countByBoard", query = "SELECT COUNT(t) FROM TeacherBoard t WHERE t.board = :board") ,
		@NamedQuery(name = "TeacherBoard.findByTeacher", query = "SELECT t FROM TeacherBoard t WHERE t.teacher = :teacher") 
})
public class TeacherBoard implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TeacherBoardPK id;

	@Column(name = "is_deleted")
	private byte isDeleted;

	// bi-directional many-to-one association to Board
	@ManyToOne
	@JoinColumn(name = "board_id", nullable = false, insertable = false, updatable = false)
	private Board board;

	// bi-directional many-to-one association to Teacher
	@ManyToOne
	@JoinColumn(name = "teacher_id", nullable = false, insertable = false, updatable = false)
	private Teacher teacher;

	public TeacherBoard() {
	}

	public TeacherBoardPK getId() {
		return this.id;
	}

	public void setId(TeacherBoardPK id) {
		this.id = id;
	}

	public byte getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Board getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}