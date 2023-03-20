package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
	@NamedQuery(name = "Person.getInfoStudentByLeaderId", query = "SELECT p.personId, p.fullName, p.gender, p.address, p.phonenumber, "
			+ "p.email, p.role, p.description "
//	'GS00000005': thay thế khi có dữ liệu được gởi về từ login để tìm ra được group_id thông qua thông tin đăng nhập.		
			+ "FROM Person p, Student s, GroupStudent g WHERE g.groupId = 'GS00000005'"
			+ "AND g.groupId = s.groupstudent.groupId " + "AND s.person.personId = p.personId "
			+ "AND s.groupstudent.groupId IS NOT NULL"),
	@NamedQuery(name="Person.findPersonByEmail", query="SELECT p FROM Person p WHERE p.email = :email"),
	@NamedQuery(name = "Person.findByIsDelete", query = "SELECT p FROM Person p WHERE p.isDeleted = :isDeleted")
	})
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="person_id")
	private String personId;

	private String address;

	private String description;

	private String email;

	@Column(name="full_name")
	private String fullName;

	private byte gender;

	@Column(name="is_deleted")
	private byte isDeleted;

	private String phonenumber;

	private String role;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="person")
	private List<Account> accounts;

	//bi-directional many-to-one association to Admin
	@OneToMany(mappedBy="person")
	private List<Admin> admins;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="person1")
	private List<Notification> notifications1;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="person2")
	private List<Notification> notifications2;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="person")
	private List<Student> students;

	//bi-directional many-to-one association to Teacher
	@OneToMany(mappedBy="person")
	private List<Teacher> teachers;

	public Person() {
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public byte getGender() {
		return this.gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}

	public byte getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setPerson(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setPerson(null);

		return account;
	}

	public List<Admin> getAdmins() {
		return this.admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	public Admin addAdmin(Admin admin) {
		getAdmins().add(admin);
		admin.setPerson(this);

		return admin;
	}

	public Admin removeAdmin(Admin admin) {
		getAdmins().remove(admin);
		admin.setPerson(null);

		return admin;
	}

	public List<Notification> getNotifications1() {
		return this.notifications1;
	}

	public void setNotifications1(List<Notification> notifications1) {
		this.notifications1 = notifications1;
	}

	public Notification addNotifications1(Notification notifications1) {
		getNotifications1().add(notifications1);
		notifications1.setPerson1(this);

		return notifications1;
	}

	public Notification removeNotifications1(Notification notifications1) {
		getNotifications1().remove(notifications1);
		notifications1.setPerson1(null);

		return notifications1;
	}

	public List<Notification> getNotifications2() {
		return this.notifications2;
	}

	public void setNotifications2(List<Notification> notifications2) {
		this.notifications2 = notifications2;
	}

	public Notification addNotifications2(Notification notifications2) {
		getNotifications2().add(notifications2);
		notifications2.setPerson2(this);

		return notifications2;
	}

	public Notification removeNotifications2(Notification notifications2) {
		getNotifications2().remove(notifications2);
		notifications2.setPerson2(null);

		return notifications2;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setPerson(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setPerson(null);

		return student;
	}

	public List<Teacher> getTeachers() {
		return this.teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Teacher addTeacher(Teacher teacher) {
		getTeachers().add(teacher);
		teacher.setPerson(this);

		return teacher;
	}

	public Teacher removeTeacher(Teacher teacher) {
		getTeachers().remove(teacher);
		teacher.setPerson(null);

		return teacher;
	}

}