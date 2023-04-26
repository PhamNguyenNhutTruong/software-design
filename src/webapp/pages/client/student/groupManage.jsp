<%@page import="com.courses.services.GroupService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String context = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:useBean id="studentService"
	class="com.courses.services.admin.user.StudentService"></jsp:useBean>
<jsp:useBean id="groupService" class="com.courses.services.GroupService"></jsp:useBean>
<jsp:include page="../partials/head.jsp" />
<title>Trang chủ - Đăng ký đề tài</title>
</head>
<body>
	<div id="root">
		<!-- Header -->
		<jsp:include page="../partials/header.jsp" />
		<!-- Body -->
		<main>
			<!-- Body -->
			<div class="container">
				<div class="grid">
					<div class="grid_row">
						<div class="grid_column_1">
							<jsp:include page="./sidebar.jsp" />
						</div>
						<div class="grid_column_3">
							<div class="topic_registration">
								<ion-icon name="people-outline"></ion-icon>
								<h3>QUẢN LÝ NHÓM</h3>
							</div>
							<div class="topic_info_contaniner">
								<div class="topic_info_contaniner-item">
									<label>Tên đề tài:</label>
									<h3>${student.getGroupstudent().getTopic().getTopicName()}</h3>
								</div>
								<div class="topic_info_contaniner-item">
									<label>Giảng viên hướng dẫn:</label>
									<h3>${student.getGroupstudent().getTopic().getTeacher().getPerson().getFullName()}</h3>
								</div>
								<div class="topic_info_contaniner-item">
									<label>Số lượng sinh viên:</label>
									<h3>${student.getGroupstudent().getTopic().getMaxMoMember()}</h3>
								</div>
							</div>
							<div class="topic_registration-filter">
								<h3 class="topic_registration-filter-active">
									<a href="<%=context%>/student/group-manage/create-group">Tạo
										nhóm</a>
								</h3>
								
								<h3
									class="topic_registration-filter-active topic_registration-message">
									<a href="<%=context%>/student/group-manage/add-memeber">Thêm
										thành viên </a>
									<div
										class="topic_registration-join-group ${sessionScope.joinGroups == null ? 'hide_element' : '' }">${sessionScope.joinGroups.size()}</div>
								</h3>
							
								<h3
									class="topic_registration-filter-active topic_registration-message">
									<a href="#" data-bs-toggle="modal" data-bs-target="#addMemberToGroupModal">Thêm
										thành viên </a>
									<div
										class="topic_registration-join-group ${sessionScope.joinGroups == null ? 'hide_element' : '' }">${sessionScope.joinGroups.size()}</div>
								</h3>
								<h3 class="topic_registration-filter-active">
									<a href="<%=context%>/topic-registration/change-topic">Đổi
										đề tài</a>
								</h3>
							</div>
							<!-- when: Nếu chưa có nhóm hiển thị giao diện chưa có nhóm -->
							<!-- otherwise: Đã có nhóm hiển thị danh sách nhóm -->
							<c:choose>
								<c:when test="${uiGroupManage == null}">
									<p class="topic_registration-notification highlight_content">Bạn
										chưa có nhóm nào</p>
									<!-- Chức năng hiện thị danh sách các nhóm - để sinh viên chưa có nhóm có thể xin vào nhóm -->
								</c:when>
								<c:otherwise>
									<div class="topic_registration-detail">
										<div class="group_topic_registration-to-manage">
											<table>
												<tr>
													<th width="20%">Mã sinh viên</th>
													<th width="45%">Tên sinh viên</th>
													<th width="20%">Chức vụ</th>
													<th class="hide_element">Xem chi tiết</th>
												</tr>
											</table>
										</div>
										<c:forEach var="item" items="${students}">
											<c:choose>
												<c:when test="${item.getPerson().getIsDeleted() == 0}">
													<div class="group_topic_registration-to-manage">
														<table>
															<tr>
																<th width="20%" class="highlight_content">${item.getStudentId()}</th>
																<th width="45%">${item.getPerson().getFullName()}</th>
																<th width="20%">${item.getGroupstudent().getLeaderId() == item.getStudentId() ? 'Trưởng Nhóm' : 'Thành Viên'}</th>
																<th><a
																	href="<%=context%>/student/group-manage/delete-memeber?student_id=${item.getStudentId()}">${groupService.grantPermissionDelete(sessionScope.username) == 'leader' ? (item.getGroupstudent().getLeaderId() == item.getStudentId() ? '' : 'Xóa' ) : (sessionScope.username == item.getPerson().getEmail() ? 'Thoát nhóm' : '')}</a></th>
															</tr>
														</table>
													</div>
												</c:when>
											</c:choose>
										</c:forEach>
									</div>
								</c:otherwise>
							</c:choose>
							<!-- 
							<p class="topic_registration-notification highlight_content">Bạn
								chưa có nhóm nào</p>							
							 -->
							<div class="seperate_boder"></div>
							<c:if test="${groupStudents.size() != 0}">
								<div class="topic_registration">
									<ion-icon name="receipt-outline"></ion-icon>
									<h3>NHÓM SINH VIÊN</h3>
								</div>
								<div class="topic_registration-detail">
									<div class="group_topic_registration-to-manage">
										<table>
											<tr>
												<th width="15%">Mã nhóm</th>
												<th width="30%">Tên nhóm trưởng</th>
												<th width="15%">Số lượng</th>
												<th width="30%">Tên đề tài</th>
												<th width="10%" class="hide_element">Tham gia</th>
											</tr>
										</table>
									</div>
									<c:forEach var="item" items="${groupStudents}">
										<div class="group_topic_registration-to-manage">
											<table>
												<tr>
													<th width="15%" class="highlight_content">${item.getGroupId()}</th>
													<th width="30%">${studentService.getFullNameLeader(item.getLeaderId())}</th>
													<th width="15%">${groupService.getNumberOfMemberNotDeleteInGroup(item.getGroupId())}${item.getTopic() != null ?  '/' += item.getTopic().getMaxMoMember() : ''}</th>
													<th width="30%">${item.getTopic().getTopicName() }</th>
													<th width="10%" class="${uiGroupManage == null ? '' : 'hide_element'}">
														<a
														href="<%=context%>/student/join-group?groupt_id=${item.getGroupId()}">${item.getCurrentNumber() < item.getTopic().getMaxMoMember() ? 'Tham gia' : ''}</a>
													</th>	
												</tr>
											</table>
										</div>
									</c:forEach>
								</div>
							</c:if>
						</div>
					</div>

				</div>
			</div>
		</main>
		<!-- Button trigger modal -->
		<!-- Modal -->
		<div class="modal fade" id="addMemberToGroupModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
				<h5 class="modal-title" style="font-size: 20px; font-weight: bold">
				REGISTER GROUP STUDENT
				</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			  </div>
			  <div class="modal-body">
					<div class="mb-3">
						<label for="studentIdInput" class="form-label">Student ID</label>
						<input style="font-size: 16px"
						type="text" class="form-control" id="studentIdInput" placeholder="Enter student ID" required="required">
					</div>
					<button type="submit" class="btn btn-primary py-3 addMemberToGroupBtn" style="width: 100%; font-size: 14px">Register</button>
			  </div>
			  <div class="modal-footer">
					<c:if test="${sessionScope.joinGroups != null}">
					<h3 style="width: 100%; text-align: center;">Danh sách sinh viên xin tham gia vào nhóm</h3>
					<div class="join__group-container-item" style="width: 100%">
						<table>
							<tr>
								<th width="40%">Họ và tên</th>
								<th width="40%">Email</th>
								<th class="hide_element">Xem chi tiết</th>
							</tr>
						</table>
					</div>
					<c:forEach var="item" items="${sessionScope.joinGroups}">
						<div class="join__group-container-item" style="width: 100%">
							<table>
								<tr>
									<th width="40%">${item.getStudent().getPerson().getFullName() }</th>
									<th width="40%">${item.getStudent().getPerson().getEmail() }</th>
									<th><a href="<%=context %>/add-member-to-group?studentId=${item.getStudent().getStudentId()}" class="highlight_content"> <ion-icon
												name="checkmark-circle-outline"></ion-icon>
									</a> <a href="<%=context %>/student/delete-to-group?studentId=${item.getStudent().getStudentId()}" class="highlight_content"> <ion-icon
												name="close-circle-outline"></ion-icon>
									</a></th>
								</tr>
							</table>
						</div>
					</c:forEach>
				</c:if>
				  </div>
				</div>
			  </div>
		</div>
		<!-- Modal -->
		<jsp:include page="../partials/logoutModal.jsp"></jsp:include>
		<!-- Footer -->
		<jsp:include page="../partials/footer.jsp" />
		
		
	</div>
	<input type="text" id="isCreateGroup" value="${isCreateGroup}" hidden="true" />
	<input type="text" id="isAddMember" value="${isAddMember}" hidden="true" />
	<input type="text" id="isDeleteMember" value="${isDeleteMember}" hidden="true" />
	<input type="text" id="isCancelRequest" value="${isCancelRequest}"
		hidden="true" />
	<input type="text" id="isJoinGroup" value="${isJoinGroup}" hidden="true" />
	<input type="text" id="isChangeTopic" value="${isChangeTopic}" hidden="true" />
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

	<script type="text/javascript">
		let url = window.location.origin;
		if(window.location.protocol === 'http:') {
			url = url + '/CoursesRegistrationApp';
		}
		url = url + '/add-member-to-group';
		console.log(url);
		
		$('.addMemberToGroupBtn').click(() => {
			const studentId = $('#studentIdInput').val();
			console.log(studentId);
			$.ajax({
			    url,
			    type: 'GET',
			    data: {studentId},
			    dataType: 'json',
			    success: function(response) {
			    	console.log('success');
			    	const href = window.location.href;
			    	if(href.contains('delete-memeber')) {
			    		window.location.href = url;		
			    	} else {
			    		location.reload();
			    	}
			    },
			    error: function(xhr) {
			    	console.log('error');
			    	const href = window.location.href;
			    	if(href.includes('delete-memeber')) {
			    		window.location.href = url;		
			    	} else {
			    		location.reload();
			    	}
			    }
			});
		})
	</script>
	<script>
		const isCreateGroup = $('#isCreateGroup').val();
		const isAddMember = $('#isAddMember').val();
		const isDeleteMember = $('#isDeleteMember').val();
		const isCancelRequest = $('#isCancelRequest').val();
		const isJoinGroup = $('#isJoinGroup').val();
		const isChangeTopic = $('#isChangeTopic').val();

		if (isCreateGroup === 'FAILED') {
			swal("Thông báo!", "Tạo nhóm thất bại", "error");
		} else if (isCreateGroup === 'SUCCESS') {
			swal("Thông báo!", "Tạo nhóm thành công", "success");
		}

		if (isAddMember === 'FAILED') {
			swal(
					"Thông báo!",
					"Thêm thành viên thất bại. Cần đảm bảo các yếu tố sau:\n- Bạn phải là trưởng nhóm\n- Cần đăng kí đề tài trước khi thêm thành viên vào nhóm\n- Thành viên cần thêm phải hợp lệ( Đúng MSSV; Chưa có nhóm; Chưa bị xóa khỏi danh sách quản lý)\n- Số lượng thành viên không vượt quá qui định của đề tài",
					"error");
		} else if (isAddMember === 'SUCCESS') {
			swal("Thông báo!", "Thêm thành viên thành công", "success");
		}

		if (isDeleteMember === 'FAILED') {
			swal("Thông báo!", "Xóa thành viên thất bại", "error");
		} else if (isDeleteMember === 'SUCCESS') {
			swal("Thông báo!", "Xóa thành viên thành công", "success");
		}

		if (isCancelRequest === 'FAILED') {
			swal("Thông báo!", "Xóa yêu cầu tham gia vào nhóm thất bại",
					"error");
		} else if (isCancelRequest === 'SUCCESS') {
			swal("Thông báo!", "Xóa yêu cầu tham gia vào nhóm thành công",
					"success");
		}

		if (isJoinGroup === 'FAILED') {
			swal("Thông báo!", "Xin tham gia vào nhóm thất bại", "error");
		} else if (isJoinGroup === 'SUCCESS') {
			swal(
					"Thông báo!",
					"Xin tham gia vào nhóm thành công. Hãy chờ đợi để được xét duyệt vào nhóm",
					"success");
		}

		if (isChangeTopic === 'FAILED') {
			swal(
					"Thông báo!",
					"Để có thể thay đổi đề tài cần phải có các điều kiện:\n- Phải là trưởng nhóm\n- Đã đăng kí đề tài trước đó",
					"error");
		}
	</script>
</body>
</html>
