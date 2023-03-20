<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String context = request.getContextPath();
String check = (String) request.getAttribute("notExistPeriod");
%>



<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../partials/head.jsp" />
<title>Trang chủ - Đăng ký đề tài</title>
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
								<h3>QUẢN LÝ ĐỀ TÀI</h3>
							</div>
							<div class="topic_registration-filter d-flex">
								<div class="mx-2">
									<h3 class="topic_registration-filter-active">
										<a href="<%=context%>/teacher/topic-manage?select=0">Chưa
											được đăng ký</a>
									</h3>
								</div>
								<div class="mx-2">
									<h3>
										<a href="<%=context%>/teacher/topic-manage?select=1">Đã
											được đăng ký</a>
									</h3>
								</div>

								<div class="mx-2">
									<h3>
										<a href="<%=context%>/teacher/topic-manage?status=0">Chưa
											được duyệt </a>
									</h3>
								</div>

								<div class="mx-2">
									<h3>
										<a href="<%=context%>/teacher/topic-manage/add">Thêm đề
											tài</a>
									</h3>
								</div>
							</div>
							<div class="topic_registration-detail">
								<div class="group_topic_registration-to-manage">
									<table>
										<tr>
											<th width="30%">Tên đề tài</th>
											<th width="25%">Đợt đề xuất</th>
											<th width="25%">Năm học</th>
											<th class="hide_element" width="10%">Xem chi tiết</th>
											<th class="hide_element" width="10%">Xem chi tiết</th>
										</tr>
									</table>
								</div>
								<c:forEach var="topic" items="${topics }">
									<div class="group_topic_registration-to-manage">
										<table>
											<tr>
												<th width="30%" class="highlight_content">${topic.topicName}</th>
												<th width="25%">${topic.registrationperiod.registrationPeriodName}</th>
												<th width="25%">${topic.registrationperiod.schoolYear}</th>
												<th width="10%"><a
													href="<%=context%>/teacher/topic-manage/detail?topic=${topic.getTopicId()}"
													class="highlight_content">Chi tiết</a></th>
												<th width="10%"><a
													href="<%=context%>/teacher/topic-manage/delete/?topic=${topic.getTopicId()}"
													class="highlight_content">Xóa</a></th>
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
		<input id="deletedTopicStatus"
			value="${sessionScope.deletedTopicStatus}" hidden/>
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
	<jsp:include page="../partials/script.jsp" />
	<script>
		if ($('#deletedTopicStatus').val() == 'success') {
			Swal.fire('Thông báo', 'Xóa đề tài thành công', 'success')
		}
	</script>
</body>
</html>