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
<title>Xét duyệt đề tài - Đăng ký đề tài</title>
</head>
<body>
	<div id="root">
		<!-- Header -->
		<jsp:include page="../../partials/header.jsp" />
		<!-- Body -->
		<main id="main">
			<div class="container">
				<div class="grid">
					<div class="grid_row">
						<div class="grid_column_1">
							<jsp:include page="../teacherSidebar.jsp" />
						</div>
						<div class="grid_column_3">
							<div class="topic_registration">
								<ion-icon name="home-outline"></ion-icon>
								<h3>XÉT DUYỆT ĐỀ TÀI</h3>
							</div>
							<!-- avai -->
							<div class="topic_registration-detail">
								<div class="group_topic_registration-to-manage">
									<table>
										<tr>
											<th width="30%">Tên đề tài</th>
											<th width="20%">Người đề xuất</th>
											<th width="20%">Chuyên ngành</th>
											<th width="10%">Thành viên</th>
											<th width="10%"></th>
											<th width="10%"></th>
										</tr>
									</table>
								</div>
								<c:forEach var="item" items="${topics}">
									<div class="group_topic_registration-to-manage">
										<table>
											<tr>
												<th width="30%" class="highlight_content bold_content">${item.getTopicName()}</th>
												<th width="20%" class="bold_content">${item.getTeacher().getPerson().getFullName()}</th>
												<th width="20%" class="bold_content">${item.getMajor().getMajorName()}</th>
												<th width="10%">3</th>
												<th width="10%"><a
													href="<%=context%>/teacher/approval/deail/?topic_id=${item.getTopicId()}">Xem
														chi tiết</a></th>
												<th width="10%"><a
													href="<%=context%>/teacher/approval/accept/?topic_id=${item.getTopicId()}">Duyệt</a></th>
											</tr>
										</table>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
		<input id="acceptApprovalTopicStatus"
			value="${sessionScope.acceptApprovalTopicStatus}"  hidden="true"/>

		<!-- Footer -->
		<jsp:include page="../../partials/logoutModal.jsp"></jsp:include>
		<jsp:include page="../../partials/footer.jsp" />
		<jsp:include page="../../partials/script.jsp" />
	</div>
	<script>
		if ($('#acceptApprovalTopicStatus').val() == 'success') {
			Swal.fire('Thông báo', 'Duyệt đề tài thành công', 'success');
		}
	</script>
</body>
</html>