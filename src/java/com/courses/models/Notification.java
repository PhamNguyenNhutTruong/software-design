package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the notification database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
		@NamedQuery(name = "Notification.getNotificationByLoginAccount", query = "SELECT n FROM Notification n WHERE n.person2.personId = :personId ORDER BY n.time DESC"),
		@NamedQuery(name = "Notification.getNotificationsByPerson", query = "SELECT n FROM Notification n WHERE n.person2 = :person2 ORDER BY n.time DESC")

})
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "notification_id")
	private String notificationId;

	private String content;

	@Column(name = "notification_title")
	private String notificationTitle;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	// bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private Person person1;

	// bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private Person person2;

	public Notification() {
	}

	public String getNotificationId() {
		return this.notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNotificationTitle() {
		return this.notificationTitle;
	}

	public void setNotificationTitle(String notificationTitle) {
		this.notificationTitle = notificationTitle;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Person getPerson1() {
		return this.person1;
	}

	public void setPerson1(Person person1) {
		this.person1 = person1;
	}

	public Person getPerson2() {
		return this.person2;
	}

	public void setPerson2(Person person2) {
		this.person2 = person2;
	}

}