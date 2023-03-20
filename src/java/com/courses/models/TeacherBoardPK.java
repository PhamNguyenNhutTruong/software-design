package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the teacherboard database table.
 * 
 */
@Embeddable
public class TeacherBoardPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="teacher_id", insertable=false, updatable=false, unique=true, nullable=false, length=12)
	private String teacherId;

	@Column(name="board_id", insertable=false, updatable=false, unique=true, nullable=false, length=12)
	private String boardId;

	public TeacherBoardPK() {
	}
	public String getTeacherId() {
		return this.teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getBoardId() {
		return this.boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TeacherBoardPK)) {
			return false;
		}
		TeacherBoardPK castOther = (TeacherBoardPK)other;
		return 
			this.teacherId.equals(castOther.teacherId)
			&& this.boardId.equals(castOther.boardId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.teacherId.hashCode();
		hash = hash * prime + this.boardId.hashCode();
		
		return hash;
	}
}