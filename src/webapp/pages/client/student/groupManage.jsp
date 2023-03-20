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
								<h3 class="topic_registration-filter-active">
									<a href="<%=context%>/topic-registration/change-topic">Đổi đề tài</a>
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
													<th width="15%">${item.getCurrentNumber()}${item.getTopic() != null ?  '/' += item.getTopic().getMaxMoMember() : ''}</th>
													<th width="30%">${item.getTopic().getTopicName() }</th>
													<th width="10%"
														class="${uiGroupManage == null ? '' : 'hide_element'}">
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
		<!-- Modal -->
		<jsp:include page="../partials/logoutModal.jsp"></jsp:include>
		<!-- Footer -->
		<jsp:include page="../partials/footer.jsp" />
	</div>
	<input type="text" id="isCreateGroup" value="${isCreateGroup}" hidden />
	<input type="text" id="isAddMember" value="${isAddMember}" hidden />
	<input type="text" id="isDeleteMember" value="${isDeleteMember}" hidden />
	<input type="text" id="isCancelRequest" value="${isCancelRequest}" hidden />
	<input type="text" id="isJoinGroup" value="${isJoinGroup}" hidden />
	<input type="text" id="isChangeTopic" value="${isChangeTopic}" hidden />
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

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
					"Thêm thành viên thất bại. Cần đảm bảo các yếu tố sau:\n- Bạn phải là trưởng nhóm\n- Cần đăng kí đề tài trước khi thêm thành viên vào nhóm\n- Thành viên cần thêm phải hợp lệ( Đúng MSSV; Chưa có nhóm)\n- Số lượng thành viên không vượt quá qui định của đề tài",
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
			swal("Thông báo!", "Để có thể thay đổi đề tài cần phải có các điều kiện:\n- Phải là trưởng nhóm\n- Đã đăng kí đề tài trước đó", "error");
		} 
	</script>
</body>
</html>
