package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the joingroup database table.
 * 
 */
@Embeddable
public class JoinGroupPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="group_id", insertable=false, updatable=false, unique=true, nullable=false, length=12)
	private String groupId;

	@Column(name="student_id", insertable=false, updatable=false, unique=true, nullable=false, length=12)
	private String studentId;

	public JoinGroupPK() {
	}
	public String getGroupId() {
		return this.groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getStudentId() {
		return this.studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JoinGroupPK)) {
			return false;
		}
		JoinGroupPK castOther = (JoinGroupPK)other;
		return 
			this.groupId.equals(castOther.groupId)
			&& this.studentId.equals(castOther.studentId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.groupId.hashCode();
		hash = hash * prime + this.studentId.hashCode();
		
		return hash;
	}
}