package com.courses.services.admin.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.dao.GroupStudentDAO;
import com.courses.dao.StudentDAO;
import com.courses.models.GroupStudent;
import com.courses.models.Person;
import com.courses.models.Student;
import com.courses.services.NotificationService;
import com.courses.services.SuperService;

public class StudentService extends SuperService {

	StudentDAO studentDAO = null;

	public StudentService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.studentDAO = new StudentDAO();
	}
	
	public StudentService() {}

	public void handleGetStudent() throws ServletException, IOException {
		try {
			String pageUrl = "/pages/admin/user/student/user.jsp";
			List<Student> students = this.studentDAO.findAll();
			this.request.setAttribute("users", students);
			this.request.getRequestDispatcher(pageUrl).forward(request, response);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			String errorPageUrl = "/pages/500.jsp";
			this.request.getRequestDispatcher(errorPageUrl).forward(request, response);
		}
	}

	public void handleGetEditStudent() throws ServletException, IOException {
		String pageUrl = "/pages/admin/user/student/editUser.jsp";
		try {
		} catch (Exception e) {
			pageUrl = "/pages/admin/user/student/editUser.jsp";
		}
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
	}
	
//	Kiểm tra xem người dùng vừa mới đăng nhập vào có phải là sinh viên và đồng thời chưa có nhóm hay không
	public List<Student> checkStudentAndGroup(Map<String,Object> map) {
		List<Student> students = this.studentDAO.findWithNamedQuery("Student.checkStudentAndGroup", map);
		return students;
	}
	
//	Lấy thông tin của học sinh dựa vào id(Có thể lấy được cả nhóm trưởng và khoogn có nhóm trưởng)
	public Student getStudentByStudentId(String studentId) {
		Student student = new Student();
//		Sex thay thế "ST00000002" bằng studentId sau khi đã có dữ liệu từ login trả về.
		student = this.studentDAO.find(studentId);
		return student;
	}
	
	public Student getStudentByPersonToLoginData() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Student student = new Student();
		Person person = new Person();
		
		PersonService personService = new PersonService(request, response);
		
		StudentDAO studentDAO = new StudentDAO();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		person = personService.getPersonByEmail(username);
		
		map.put("personId", person.getPersonId());
		student = studentDAO.findWithNamedQuery("Student.getStudentByPersonId", map).get(0);
		return student;
	}
	

	
//	Lấy danh sách các sinh viên trong cùng 1 group
	public List<Student> getStudentTheSameGroupByGroupId(Map<String,Object> map){
		List<Student> students = this.studentDAO.findWithNamedQuery("Student.getStudentTheSameGroupByGroupId", map);
		return students;
	}
	
	
//	Đầu tiên dùng id của người đăng nhập ST00000002 để lấy ra group_id = GS00000005(Student)
//	Sau đó dùng group_id = GS00000005 lấy ra DANH SÁCH các person_id(Student) có group_id(Student) = GS00000005  vừa tìm được
//	Duyệt qua DANH SÁCH các person_id(Student) để lấy ra danh sách các person(Person)
	public List<Student> getListStudentTheSameGroup(String studentId) {
		Student student = new Student();
		StudentService studentService = new StudentService(request, response);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Student> students = new ArrayList<Student>();
		
		student = studentService.getStudentByStudentId(studentId);
		String groupId = student.getGroupstudent().getGroupId();
		map.put("groupId", groupId);
		students = studentService.getStudentTheSameGroupByGroupId(map);
		return students;
	}
	
//	Xóa 1 sinh viên ra khỏi nhóm
	public void deleteStudenFromStudentGroup () throws ServletException, IOException {
		String isDeleteMember = "";
		try {
			String pageUrl = "/student/group-manage";
			Student student = new Student();
			GroupStudent groupStudent = new GroupStudent();
			NotificationService notificationService = new NotificationService(request, response);
			StudentService studentService = new StudentService(request, response);
			
			String leaderId = studentService.getStudentByPersonToLoginData().getStudentId();
			
			StudentDAO studentDAO = new StudentDAO();
			GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
			String student_id = this.request.getParameter("student_id");
			student = studentDAO.find(student_id);
			groupStudent = student.getGroupstudent();
			groupStudent.setCurrentNumber(groupStudent.getCurrentNumber() - 1);
			student.setGroupstudent(null);
			studentDAO.update(student);
			groupStudentDAO.update(groupStudent);
			
			notificationService.addNotification(leaderId, student_id, "Thông báo về quản lí nhóm",
					"Xin thông báo đến sinh viên " + student.getPerson().getFullName() + " bạn đã bị mời ra khỏi nhóm "
							+ groupStudent.getGroupId());
			isDeleteMember = "SUCCESS";
			this.request.setAttribute("isDeleteMember", isDeleteMember);
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		} catch (Exception e) {
			System.out.print(e.toString());
//			String pageUrl = "/pages/500.jsp";
			String pageUrl = "/student/group-manage";
			isDeleteMember = "FAILED";
			this.request.setAttribute("isDeleteMember", isDeleteMember);
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		}
	}
	
	public String getFullNameLeader(String student_id) {
		StudentDAO studentDAO = new StudentDAO();
		Student student = new Student();
		student = studentDAO.find(student_id);
		return student.getPerson().getFullName();
	}
	
	public List<Student> findStudentByGroup(GroupStudent group) {
		List<Student> students = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("group", group);
		students = this.studentDAO.findWithNamedQuery("Student.findStudentByGroup", map);
		return students;
	}

}
