package com.courses.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.GroupStudentDAO;
import com.courses.dao.StudentDAO;
import com.courses.models.GroupStudent;
import com.courses.models.Student;
import com.courses.services.admin.user.StudentService;

public class RegisterGroupService extends SuperService {
	GroupStudentDAO groupStudentDAO = null;
	public RegisterGroupService( HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.groupStudentDAO = new GroupStudentDAO();
	}
	
	public RegisterGroupService() {}
	
//	String studentId: Biến dùng để truyền thông tin của account đăng nhập(Lấy mã sinh viên hay mã định danh thay thế cho "ST00000002": thông tin được fix cứng)
	public void createGroupStudent(String studentId) throws ServletException, IOException {
		RegisterGroupService registerGroupService = new RegisterGroupService(request, response);
		StudentService studentService = new StudentService(request, response);
		GroupService groupService = new GroupService(request, response);
		
		GroupStudent groupStudent = new GroupStudent();
		Student student = new Student();
		
		GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
		StudentDAO studentDAO = new StudentDAO();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String isCreateGroup = "";
		
		registerGroupService.setEncoding();
		
		try {
			String pageUrl = "/student/group-manage";
//			Kiểm tra xem cặp key(Biến được dùng trong query name) - value(giá trị cần kiểm tra) có tồn tại hay không.
			map.put("studentId", studentId);
			List<Student> students = studentService.checkStudentAndGroup(map);
//			Nếu trả về lớn hơn 0(Sinh được kiểm tra chưa có nhóm) thì thực hiện chèn Group mới
			if(students.size() > 0) {
//			Tạo Group mới với thông tin đã check
//				Cần viết hàm tạo tự động
				groupStudent.setGroupId(groupService.randomIdNotDuplicate());
//				"ST00000002": thông tin được fix cứng để test --> Lấy thông tin từ login trả về để đưa vào
				groupStudent.setLeaderId(studentId);
				groupStudent.setDescription("Đây là sinh viên vừa mới tạo nhóm");
				groupStudent.setIsFull((byte)0);
				groupStudent.setCurrentNumber(1);
				groupStudent.setTopic(null);
				groupStudentDAO.create(groupStudent);
				
//			Cập nhật nhóm cho Student.
//				Lấy ra studnet đầu tiên trong list
				student = students.get(0);
//				Xóa đi student đó để fix lỗi Duplicate entry 'student_id' for key 'student.PRIMARY'
//				studentDAO.delete(students.get(0).getStudentId());
//				Cập nhật lại bộ thông tin mới 
				student.setGroupstudent(groupStudent);
				studentDAO.update(student);
				isCreateGroup = "SUCCESS";
			} else {
				isCreateGroup = "FAILED";
			}
			this.request.setAttribute("isCreateGroup", isCreateGroup);
			this.request.setAttribute("uiGroupManage", students);
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		} catch (Exception e) {
			System.out.print(e.toString());
//			String pageUrl = "/pages/500.jsp";
			String pageUrl = "/student/group-manage";
			isCreateGroup = "FAILED";
			this.request.setAttribute("isCreateGroup", isCreateGroup);
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		}
	}
}
