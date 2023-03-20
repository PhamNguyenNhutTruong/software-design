<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="teacherBoardDAO"
	class="com.courses.dao.TeacherBoardDAO" />
<%
String context = request.getContextPath();
String check = (String) request.getAttribute("notExistPeriod");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../partials/head.jsp" />
<title>Danh sách hội đồng của tôi - Đăng ký đề tài</title>
</head>
<body>
	<div id="root">
		<!-- Header -->
		<jsp:include page="../partials/header.jsp" />
		<!-- Body -->
		<main>
			<div class="container">
				<div class="grid">
					<div class="grid_row">
						<div class="grid_column_1">
							<jsp:include page="./teacherSidebar.jsp" />
						</div>
						<div class="grid_column_3">
							<div class="topic_registration">
								<ion-icon name="pencil"></ion-icon>
								<h3>QUẢN LÝ HỘI ĐỒNG</h3>
							</div>
							<div class="topic_registration-filter d-flex">
								<div class="mx-2">
									<h3 class="topic_registration-filter-active">
										<a href="<%=context%>/teacher/topic-manage?select=0">Tất
											cả hội đồng</a>
									</h3>
								</div>
								<div class="mx-2">
									<h3>
										<a href="<%=context%>/teacher/topic-manage?select=1">Hội
											đồng đủ thành viên</a>
									</h3>
								</div>
							</div>
							<div class="topic_registration-detail">
								<div class="group_topic_registration-to-manage">
									<table>
										<tr>
											<th width="20%">Mã hội đồng</th>
											<th width="40%">Tên hội đồng</th>
											<th width="25%">Thành viên</th>
											<th class="hide_element" width="15%">Xem chi tiết</th>
										</tr>
									</table>
								</div>
								<c:forEach var="item" items="${teacherBoards}">
									<div class="group_topic_registration-to-manage">
										<table>
											<tr>
												<th width="20%" class="highlight_content">${item.getBoard().getBoardId()}</th>
												<th width="40%">${item.getBoard().getBoardName()}</th>
												<th width="25%">${item.getBoard().getNoMember()}</th>
												<th><a width="15%"
													href="<%=context%>/teacher/board/normal/detail/?board_id=${item.getBoard().getBoardId()}"
													class="highlight_content">Chi tiết</a></th>
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
		<!-- Modal -->
		<jsp:include page="./periodModal.jsp" />
		<jsp:include page="../partials/logoutModal.jsp" />
		<!-- Footer -->
		<jsp:include page="../partials/footer.jsp" />

		<%
		if (check != null) {
		%>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#periodModal").modal('show');
			});
		</script>
		<%
		}
		%>
	</div>
</body>
</html>