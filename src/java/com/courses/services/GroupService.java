package com.courses.services;

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
import com.courses.dao.TopicDAO;
import com.courses.models.GroupStudent;
import com.courses.models.JoinGroup;
import com.courses.models.Person;
import com.courses.models.Student;
import com.courses.models.Topic;
import com.courses.services.admin.user.PersonService;
import com.courses.services.admin.user.StudentService;

public class GroupService extends SuperService {

	private GroupStudentDAO groupDAO = new GroupStudentDAO();

	public GroupService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.groupDAO = new GroupStudentDAO();
	}

	public GroupService() {
	}

	public void handleGetGroupManage() throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = "/pages/client/student/groupManage.jsp";
		StudentService studentService = new StudentService(request, response);
		JoinGroupService joinGroupService = new JoinGroupService(request, response);
		GroupService groupService = new GroupService(request, response);
		Student student = new Student();
		List<Student> students = new ArrayList<Student>();
		List<JoinGroup> joinGroups = new ArrayList<JoinGroup>();
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		Map<String, Object> map = new HashMap<String, Object>();
		String studentId = studentService.getStudentByPersonToLoginData().getStudentId();
		map.put("studentId", studentId);
		students = studentService.checkStudentAndGroup(map);
		if (students.size() == 0) {
//			Khi có dữ liệu trả về từ login thì thay cho "ST00000002"
			students = studentService.getListStudentTheSameGroup(studentId);
			student = studentService.getStudentByStudentId(studentId);
			request.setAttribute("uiGroupManage", "NOT NULL");
			request.setAttribute("students", students);
			request.setAttribute("student", student);
		}
		joinGroups = joinGroupService.getRequestJoinGroup(studentId);
		groupStudents = groupService.getGroupStudent();
		this.request.setAttribute("groupStudents", groupStudents);
		session.setAttribute("joinGroups", joinGroups);
		this.request.getRequestDispatcher(url).forward(request, response);
	}

	public void handleGetListGroup() throws ServletException, IOException {
		try {
			String pageUrl = "/pages/admin/group/group.jsp";
			List<GroupStudent> groups = this.groupDAO.findAll();
			this.request.setAttribute("groups", groups);
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String errorUrl = "/pages/500.jsp";
			this.request.getRequestDispatcher(errorUrl).forward(request, response);
		}
	}

	public void handleGetCreateGroupForm() throws ServletException, IOException {
		String pageUrl = "/pages/admin/group/editGroup.jsp";
		try {
			String groupId = this.request.getParameter("id");
			GroupStudent foundGroup = groupDAO.find(GroupStudent.class, groupId);
			request.setAttribute("group", foundGroup);
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		} catch (Exception e) {
			String errorUrl = "/pages/500.jsp";
			this.request.getRequestDispatcher(errorUrl).forward(request, response);
		}
	}

	public List<GroupStudent> checkLeader(Map<String, Object> map) {
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		groupStudents = this.groupDAO.findWithNamedQuery("GroupStudent.checkLeader", map);
		return groupStudents;
	}

	public void choiceTopic(String studentId, Topic topic) {
//		cần phải kiểm tra sinh viên đó có nhóm hay chưa. Đã có nhóm mới cho tạo.
//		sinh viên đó phải là trưởng nhóm.
//		Lấy ra được nhóm cần group sinh viên cần chọn đề tài
		GroupService groupService = new GroupService(request, response);
		GroupStudent groupStudent = null;
		TopicDAO topicDAO = new TopicDAO();
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		Map<String, Object> map = new HashMap<String, Object>();

//		Kiểm tra xe người đăng nhập vào có phải là trưởng nhóm hay ko
//		"ST00000002": sẽ được lấy từ login trả về
		map.put("leaderId", studentId);

		groupStudents = groupService.checkLeader(map);
// 		Kiểm tra xe người đăng nhập vào có phải là trưởng nhóm hay ko và có phải sinh viên hay không
		if (groupStudents.size() > 0) {
			topic.setIsSelected((byte) 1);
			groupStudent = groupStudents.get(0);
//			Kiểm tra xem số lượng thành viên của nhóm sinh viên đó có lớn hơn số lượng ho phép hay không
			if (groupStudent.getCurrentNumber() <= topic.getMaxMoMember()) {
				groupStudent.setTopic(topic);
				this.groupDAO.update(groupStudent);
				topicDAO.update(topic);
			} else {
				this.request.setAttribute("message", "Số lượng thành viên của nhóm vượt quá số lượng cho phép");
			}
		} else {
			this.request.setAttribute("message", "Bạn chưa có nhóm. Hoặc đã đăng kí đề tài");
		}
	}

	public String randomIdNotDuplicate() {
		GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
		String id = "";
		do {
			id = groupStudentDAO.randomId();
		} while (groupStudentDAO.find(id) != null);
		return id;
	}

	public List<GroupStudent> getGroupStudent() {
		GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isDeleted", (byte) 0);

		groupStudents = groupStudentDAO.findWithNamedQuery("GroupStudent.getGroupStudent", map);
		return groupStudents;
	}
	
	public int getNumberOfMemberNotDeleteInGroup(String groupId) {
		int count = 0;
		try {
			List<GroupStudent> groupStudents = this.getGroupStudent();
			List<Student> students = new ArrayList<>();
			GroupStudent groupStudent = null;
			for (GroupStudent gStudent : groupStudents) {
				if(gStudent.getGroupId().equals(groupId)) {
					groupStudent = gStudent;
					break;
				}
			}
			
			StudentService studentService = new StudentService(request, response);
			students = studentService.findStudentByGroup(groupStudent);
			
			for(Student student : students) {
				if(student.getPerson().getIsDeleted() == 0) {
					count ++;
				}
			}
		} catch (Exception e) {
			System.out.println("==================== " + e.getMessage() + " ====================");
		}
		return count;
	}

	public List<GroupStudent> checkRole(String studentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("leaderId", studentId);
		GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();

		groupStudents = groupStudentDAO.findWithNamedQuery("GroupStudent.checkRole", map);
		return groupStudents;
	}

	public String grantPermissionDelete(String username) {
		Map<String, Object> map = new HashMap<String, Object>();
		Person person = new Person();
		Student student = new Student();
		StudentDAO studentDAO = new StudentDAO();
		PersonService personService = new PersonService(request, response);
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		person = personService.getPersonByEmail(username);
		map.put("personId", person.getPersonId());
		student = studentDAO.findWithNamedQuery("Student.getStudentByPersonId", map).get(0);
		groupStudents = checkRole(student.getStudentId());
		if (groupStudents.size() > 0) {
			return "leader";
		} else {
			return "member";
		}
	}

	public void addMemberToGroup() throws ServletException, IOException {
//		Kiểm tra xem người yêu cầu thêm thành viên có phải là nhóm trưởng hay không
//		Kiểm tra mã sinh viên vừa nhập vào đã có nhóm hay chưa. Nếu chưa mới cho thêm vào nhóm
//		Kiểm tra tính hợp lệ của id được nhập vào.
		String url = "/student/group-manage";
		String isAddMember = "";
		GroupService groupService = new GroupService(request, response);
		StudentService studentService = new StudentService(request, response);
		JoinGroupService joinGroupService = new JoinGroupService(request, response);
		NotificationService notificationService = new NotificationService(request, response);

		StudentDAO studentDAO = new StudentDAO();
		GroupStudentDAO groupStudentDAO = new GroupStudentDAO();

		Student student = new Student();
		GroupStudent groupStudent = new GroupStudent();

		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		List<Student> students = new ArrayList<Student>();
// Lấy dữ đối tượng đăng nhập vào thôgn qua login	
		String leaderId = studentService.getStudentByPersonToLoginData().getStudentId();

		Map<String, Object> map = new HashMap<String, Object>();
//		Kiểm tra xe người đăng nhập vào có phải là trưởng nhóm hay ko
//		"ST00000002": sẽ được lấy từ login trả về
		map.put("leaderId", leaderId);
		groupStudents = groupService.checkLeader(map);

		String student_id = request.getParameter("studentId");
		System.out.println("===============" + student_id + "===============");
		map.clear();
		map.put("studentId", student_id);
		students = studentService.checkStudentAndGroup(map);
		try {
//			Kiểm tra xe người đăng nhập vào có phải là trưởng nhóm hay ko và có phải sinh viên hay không
			if (groupStudents.size() > 0 && students.size() > 0) {
				isAddMember = "FAILED";
			} else {
//				Nếu sinh viên đăng nhập là nhóm trưởng thì mới có quyền thêm sinh viên mới vào nhóm.
				groupStudents = groupService.checkRole(leaderId);
				if (groupStudents.size() > 0) {
					student = students.get(0);
					groupStudent = groupStudents.get(0);
//				Kiểm tra xem số thành viên của đề tài có còn cho phép thêm mới hay không và kiểm tra sinh viên cần thêm mới có bị xóa đi hay không
					if (groupStudent.getTopic().getMaxMoMember() > groupStudent.getCurrentNumber() && student.getPerson().getIsDeleted() == 0) {
						groupStudent.setCurrentNumber(groupStudent.getCurrentNumber() + 1);
						student.setGroupstudent(groupStudents.get(0));
						groupStudentDAO.update(groupStudent);
						studentDAO.update(student);
//				Thêm thông báo về việc được ai đó thêm vào nhóm
						notificationService.addNotification(leaderId, student_id, "Thông báo về quản lí nhóm",
								"Chúc mừng sinh viên " + student.getPerson().getFullName() + " đã được tham gia vào nhóm "
										+ groupStudent.getGroupId());
//				Xóa 1 sinh viên ra khỏi hàng chò xin vào nhóm khi xin viên đó được thêm vào nhóm nào đó
//						joinGroupService.deleteRequestJoinGroup(student.getStudentId(), groupStudent.getGroupId());
						joinGroupService.deleteAllRequestJoinGroupsRelatedToStudentId(student_id);
						isAddMember = "SUCCESS";

					} else {
						isAddMember = "FAILED";
					}
				} else {
					isAddMember = "FAILED";
				}
			}
			this.request.setAttribute("isAddMember", isAddMember);
			this.request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			System.out.print(e.toString());
//			String pageUrl = "/pages/500.jsp";
			String pageUrl = "/student/group-manage";
			isAddMember = "FAILED";
			this.request.setAttribute("isAddMember", isAddMember);
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		}
	}
	
	// get group of student by topic
	public List<GroupStudent> getGroupStudentByTopic(Topic topic){
		List<GroupStudent> groups = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("topic", topic);
		if (this.groupDAO.findWithNamedQuery("GroupStudent.getGroupStudentByTopic", map).size() > 0) {
			groups = this.groupDAO.findWithNamedQuery("GroupStudent.getGroupStudentByTopic", map);
		}
		return groups;
	}
	
	// get students of a specified group
	public Map<GroupStudent, List<Student>> getGroupStudentInfomation(Topic foundTopic){
		StudentService studentService = new StudentService(request, response);
		// define a map that will contain formation about student of each group
		Map<GroupStudent, List<Student>> map = new HashMap<GroupStudent, List<Student>>();
		// find groups
		List<GroupStudent> groups = getGroupStudentByTopic(foundTopic);
		// check if there is any existing group
		if (groups != null) {
			for(GroupStudent group: groups){
				// find list student of the group
				List<Student> students = null;
				students = studentService.findStudentByGroup(group);
				//save 
				map.put(group, students);
			}
		}
		// return value
		return map;
	}
	
	public void removeSelectedTopic() throws ServletException, IOException {
		StudentService studentService = new StudentService(request, response);
		TopicService topicService = new TopicService(request, response);
		GroupStudent groupStudent = new GroupStudent();
		Topic topic = new Topic();
		GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
		TopicDAO topicDAO = new TopicDAO();
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		Map<String, Object> map = new HashMap<>();
		String isChangeTopic = "FAILED";
		String url = "/pages/client/student/groupManage.jsp";
		
//		String username = studentService.getStudentByPersonToLoginData().getPerson().getEmail();
		String studentId = studentService.getStudentByPersonToLoginData().getStudentId();
//		Kiểm tra xem đó có phải là trưởng nhóm hay không. Nếu phải thì mới cho clear checkLeader
		groupStudents = checkRole(studentId);
		if (groupStudents.size() > 0) {
			map.put("leaderId", studentId);
			groupStudents = checkLeader(map);
//			Nếu list.size() == 0 --> đã có topic. Ngược lại là chưa có topic
			if(groupStudents.size() == 0) {
				System.out.println("Đã đăng kí đề tài");
				groupStudent = checkRole(studentId).get(0);
				topic = groupStudent.getTopic();
				groupStudent.setTopic(null);
				topic.setIsSelected((byte)0);
				groupStudentDAO.update(groupStudent);
				topicDAO.update(topic);
				
				topicService.getTopic((byte)0);
			} else {
				System.out.println("Chưa đăng kí đề tài");
				this.request.setAttribute("isChangeTopic", isChangeTopic);
//				this.request.getRequestDispatcher(url).forward(request, response);
				handleGetGroupManage();
			}
		} else {
//			Cho hiện ra thông báo. Rồi cho nó quay về trang quản lí nhóm
			System.out.println("Bạn không phải là trường nhóm");
			this.request.setAttribute("isChangeTopic", isChangeTopic);
//			this.request.getRequestDispatcher(url).forward(request, response);
			handleGetGroupManage();
		}
	} 
}
