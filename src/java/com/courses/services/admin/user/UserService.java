package com.courses.services.admin.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.dao.AccountDAO;
import com.courses.dao.AdminDAO;
import com.courses.dao.PersonDAO;
import com.courses.dao.StudentDAO;
import com.courses.dao.TeacherDAO;
import com.courses.models.Account;
import com.courses.models.Admin;
import com.courses.models.Person;
import com.courses.models.Student;
import com.courses.models.Teacher;
import com.courses.services.SuperService;
import com.courses.utils.constants.RoleConstants;

public class UserService extends SuperService {

	PersonDAO personDAO = null;
	StudentDAO studentDAO = null;
	TeacherDAO teacherDAO = null;
	AdminDAO adminDAO = null;
	AccountDAO accountDAO = null;

	public UserService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.personDAO = new PersonDAO();
		this.studentDAO = new StudentDAO();
		this.teacherDAO = new TeacherDAO();
		this.adminDAO = new AdminDAO();
		this.accountDAO = new AccountDAO();
	}
	
	public UserService() {}

	public void handleGetListUser() throws ServletException, IOException {
		HttpSession session = this.request.getSession();
		String pageUrl = "/pages/admin/user/user.jsp";
		String userType = this.request.getParameter("type");
		try {
			switch (userType) {
			case RoleConstants.ADMIN:
				List<Admin> admins = this.adminDAO.findAll();
				this.request.setAttribute("users", admins);
				break;
			case RoleConstants.TEACHER:
				List<Teacher> teachers = this.teacherDAO.findAll();
				this.request.setAttribute("users", teachers);
				break;
			default:
				List<Student> s2 = this.studentDAO.findAll();
				this.request.setAttribute("users", s2);
				break;
			}

		} catch (Exception e) {
			pageUrl = "/pages/500.jsp";
		}
		this.request.setAttribute("type", userType);
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
		session.invalidate();
	}

	public void handleGetEditUserForm() throws ServletException, IOException {
		String pageUrl = "";
		Person person = null;
		Account account = null;
		// Get Params
		String userType = this.request.getParameter("type");
		String id = this.request.getParameter("id");
		try {
			pageUrl = "/pages/admin/user/" + userType + "/editUser.jsp";
			switch (userType) {
			case RoleConstants.ADMIN:
				Admin admin = this.adminDAO.find(id);
				person = admin.getPerson();
				this.request.setAttribute("user", admin);
				break;

			case RoleConstants.TEACHER:
				Teacher teacher = this.teacherDAO.find(id);
				person = teacher.getPerson();
				this.request.setAttribute("user", teacher);
				break;

			case RoleConstants.STUDENT:
				Student student = this.studentDAO.find(id);
				person = student.getPerson();
				this.request.setAttribute("user", student);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			pageUrl = "/pages/500.jsp";
		}
		if (person != null) {
			account = this.accountDAO.findByPerson(person);
		}
		this.request.setAttribute("account", account);
		this.request.setAttribute("type", userType);
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
	}

	public void handlePostCreateUser() throws ServletException, IOException {
		String type = this.request.getParameter("type");
		String pageUrl = this.request.getContextPath() + "/admin/users/?type=" + type;
		try {
			// Get params
			String personId = this.request.getParameter("personId");
			String id = this.request.getParameter("id");
			String fullname = this.request.getParameter("fullname");
			String email = this.request.getParameter("email");
			String address = this.request.getParameter("address");
			byte gender = Byte.parseByte(this.request.getParameter("gender"));
			String phonenumber = this.request.getParameter("phonenumber");
			String role = this.request.getParameter("role");
			String description = this.request.getParameter("description");

			// Create person
			Person person = new Person();
			person.setPersonId(personId);
			person.setFullName(fullname);
			person.setEmail(email);
			person.setAddress(address);
			person.setPhonenumber(phonenumber);
			person.setGender(gender);
			person.setDescription(description);
			person.setRole(role);

			this.personDAO.create(person);

			// Create inherit person
			switch (role) {
			case RoleConstants.ADMIN:
				Admin admin = new Admin();
				admin.setAdminId(id);
				admin.setPerson(person);
				this.adminDAO.create(admin);
				break;

			case RoleConstants.TEACHER:
				Teacher teacher = new Teacher();
				teacher.setTeacherId(id);
				teacher.setPerson(person);
				this.teacherDAO.create(teacher);
				break;

			case RoleConstants.STUDENT:
				Student student = new Student();
				student.setStudentId(id);
				student.setPerson(person);
				this.studentDAO.create(student);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			pageUrl = "/pages/500.jsp";
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
			return;
		}
		this.response.sendRedirect(pageUrl);
	}

	public void handleGetListUserDeleted() throws ServletException, IOException {
		String pageUrl = "/pages/admin/user/trashUser.jsp";
		String userType = this.request.getParameter("type");
		try {
			switch (userType) {
			case RoleConstants.ADMIN:
				List<Admin> admins = this.adminDAO.findAll();
				this.request.setAttribute("users", admins);
				break;
			case RoleConstants.TEACHER:
				List<Teacher> teachers = this.teacherDAO.findAll();
				this.request.setAttribute("users", teachers);
				break;
			default:
				List<Student> s2 = this.studentDAO.findAll();
				this.request.setAttribute("users", s2);
				break;
			}

		} catch (Exception e) {
			pageUrl = "/pages/500.jsp";
		}
		this.request.setAttribute("type", userType);
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
	}
	
	
	public void handleGetStudentInfo() throws ServletException, IOException {
		String url = "/pages/client/student/studentInfo.jsp";
		HttpSession session = this.request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println("===================="+ username +"=========================");
		PersonService personService = new PersonService(request, response);
		Person person = new Person();
//		person = personService.getPersonByPersonId("PE00000002");
		person = personService.getPersonByEmail(username);
		this.request.setAttribute("person", person);
		this.request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	public void restoreUser() throws ServletException, IOException {
		String pageUrl = "/pages/admin/user/trashUser.jsp";
		String isRestoreUser = "FAILED";
		String id = this.request.getParameter("id");
		String userType = this.request.getParameter("type");
		
		Person person = new Person();
		PersonDAO personDAO = new PersonDAO();
		try {
			switch (userType) {
			case RoleConstants.ADMIN:
				Admin admin = new Admin();
				AdminDAO adminDAO = new AdminDAO();
				
				admin = adminDAO.find(id);
				person = admin.getPerson();
				person.setIsDeleted((byte)0);
				personDAO.update(person);
				isRestoreUser = "SUCCESS";
				break;
			case RoleConstants.TEACHER:
				Teacher teacher = new Teacher();
				TeacherDAO teacherDAO = new TeacherDAO();
				
				teacher = teacherDAO.find(id);
				person = teacher.getPerson();
				person.setIsDeleted((byte)0);
				personDAO.update(person);
				isRestoreUser = "SUCCESS";
				break;
			default:
				Student student = new Student();
				StudentDAO studentDAO = new StudentDAO();
				
				student = studentDAO.find(id);
				person = student.getPerson();
				person.setIsDeleted((byte)0);
				personDAO.update(person);
				person.setIsDeleted((byte)0);
			
				isRestoreUser = "SUCCESS";
				break;
			}

		} catch (Exception e) {
			pageUrl = "/pages/500.jsp";
		}
		this.request.setAttribute("type", userType);
		this.request.setAttribute("isRestoreUser", isRestoreUser);
//		this.request.getRequestDispatcher(pageUrl).forward(request, response);
	}
}
