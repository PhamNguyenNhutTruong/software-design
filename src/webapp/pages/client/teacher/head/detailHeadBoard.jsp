<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String context = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../partials/head.jsp" />
<title>Chi tiết hội đồng - Đăng ký đề tài</title>
</head>
<body>
	<div id="root">
		<!-- Header -->
		<jsp:include page="../../partials/header.jsp" />
		<!-- Body -->
		<main>
			<div class="container">
				<div class="grid">
					<div class="grid_row">
						<div class="grid_column_1">
							<jsp:include page="../teacherSidebar.jsp" />
						</div>
						<div class="grid_column_3">
							<div class="topic_registration">
								<ion-icon name="person-outline"></ion-icon>
								<h3>CHI TIẾT HỘI ĐỒNG</h3>
							</div>
							<div class="topic_registration-filter d-flex">
								<div class="mx-2">
									<h3 class="topic_registration-filter-active">
										<a
											href="<%=context%>/teacher/board/head/add-member/?board_id=${boardId}">Thêm
											thành viên</a>
									</h3>
								</div>
								<div class="mx-2">
									<h3 class="topic_registration-filter-active">
										<a
											href="<%=context%>/teacher/board/head/add-group/?board_id=${boardId}">Thêm
											nhóm</a>
									</h3>

								</div>
							</div>
							<!-- student info -->
							<div class="info__student--container">
								<div class="info_student">
									<h3 class="title backgroud__info_student">THÔNG TIN HỘI
										ĐỒNG</h3>
									<ul class="list__info">
										<li class="info__item">
											<h3>Mã hội đồng:</h3>
											<h3>${board.getBoardId()}</h3>
										</li>
										<li class="info__item">
											<h3>Tên hội đồng:</h3>
											<h3>${board.getBoardName() }</h3>
										</li>
										<li class="info__item">
											<h3>Số lượng thành viên:</h3>
											<h3>${board.getNoMember()}</h3>
										</li>
										<li class="info__item">
											<h3>Mô tả:</h3>
											<h3>${board.getDescription()}</h3>
										</li>
									</ul>
								</div>
								<div class="contact__student">
									<h3 class="title backgroud__info_contact">THÔNG TIN THÀNH
										VIÊN</h3>
									<c:forEach var="item" items="${teacherBoards}">
										<div class="teacher__memeber-heading">
											<h3 class="description">* Giảng viên:
												${item.getTeacher().getPerson().getFullName()}</h3>
											<a
												href="<%=context%>/teacher/board/head/delete/?board_id=${boardId}&teacher_id=${item.getTeacher().getTeacherId()}">Xóa
												thành viên</a>
										</div>

										<ul class="list__info">
											<li class="info__item">
												<h3>Điện thoại:</h3>
												<h3>${item.getTeacher().getPerson().getPhonenumber()}</h3>
											</li>
											<li class="info__item">
												<h3>Email:</h3>
												<h3>${item.getTeacher().getPerson().getEmail() }</h3>
											</li>
											<li class="info__item">
												<h3>Địa chỉ:</h3>
												<h3>${item.getTeacher().getPerson().getAddress()}</h3>
											</li>
											<li class="info__item">
												<h3>Ghi chú:</h3>
												<h3>${item.getTeacher().getPerson().getDescription()}</h3>
											</li>
										</ul>
									</c:forEach>

								</div>
							</div>

							<div class="info__student--container">
								<div class="info_student">
									<h3 class="title backgroud__info_contact">THÔNG TIN NHÓM
										ĐƯỢC CHẤM</h3>
									<c:choose>
										<c:when test="${groupStudents.size() > 0}">
											<c:forEach var="item" items="${groupStudents}">
												<div class="teacher__memeber-heading">
													<h3 class="description">* Nhóm: ${item.getGroupId()}</h3>
													<a
														href="<%=context%>/teacher/board/head/delete-group/?board_id=${boardId}&group_id=${item.getGroupId()}">Xóa
														nhóm</a>
												</div>
												<ul class="list__info">
													<li class="info__item">
														<h3>Tên đề tài:</h3>
														<h3>${item.getTopic().getTopicName()}</h3>
													</li>
													<li class="info__item">
														<h3>Mô tả:</h3>
														<h3>${item.getDescription() }</h3>
													</li>
													<li class="info__item">
														<h3>Số lượng thành viên hiện tại:</h3>
														<h3>${item.getCurrentNumber()}</h3>
													</li>
													<li class="info__item">
														<h3>Ghi chú:</h3>
														<h3>${item.getTopic().getTopicName()}</h3>
													</li>
												</ul>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<div class="teacher__memeber-heading">
												<h3 class="description">* Chưa có nhóm</h3>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
		<input id="addedTeacherBoardStatus"
			value="${sessionScope.addedTeacherBoardStatus}" hidden="true" /> <input
			id="addedGroupBoardStatus"
			value="${sessionScope.addedGroupBoardStatus}" hidden="true" /> <input
			id="deletedTeacherBoardStatus"
			value="${sessionScope.deletedTeacherBoardStatus}" hidden="true" /> <input
			id="deletedGroupBoardStatus"
			value="${sessionScope.deletedGroupBoardStatus}" hidden="true" />
		<!-- Modal -->
		<jsp:include page="../../partials/logoutModal.jsp"></jsp:include>
		<!-- Footer -->
		<jsp:include page="../../partials/footer.jsp" />
		<jsp:include page="../../partials/script.jsp" />
	</div>
	<script>
		// addedTeacherBoardStatus
		if ($('#addedTeacherBoardStatus').val() === 'success') {
			Swal.fire('Thông báo', 'Thêm thành viên thành công', 'success')
		} else if ($('#addedTeacherBoardStatus').val() === 'fail') {
			Swal.fire('Thông báo', 'Thêm thành viên thất bại, giáo viên không tồn tại trên hệ thống', 'error')
		}

		// addedGroupBoardStatus
		if ($('#addedGroupBoardStatus').val() === 'success') {
			Swal.fire('Thông báo', 'Thêm nhóm thành công', 'success')
		} else if ($('#addedGroupBoardStatus').val() === 'fail') {
			Swal.fire('Thông báo', 'Thêm nhóm thất bại', 'error')
		}

		// deletedTeacherBoardStatus
		if ($('#deletedTeacherBoardStatus').val() === 'success') {
			Swal.fire('Thông báo', 'Xóa thành viên hội đồng thành công',
					'success')
		} else if ($('#deletedTeacherBoardStatus').val() === 'fail') {
			Swal.fire('Thông báo', 'Xóa thành viên hội đồng thất bại', 'error')
		}

		// deletedGroupBoardStatus
		if ($('#deletedGroupBoardStatus').val() === 'success') {
			Swal.fire('Thông báo', 'Xóa thành viên hội đồng thành công',
					'success')
		} else if ($('#deletedGroupBoardStatus').val() === 'fail') {
			Swal.fire('Thông báo', 'Xóa thành viên hội đồng thất bại', 'error')
		}
	</script>

</body>
</html>