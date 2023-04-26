<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String context = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../partials/head.jsp" />
<title>Trang chủ - Đăng ký đề tài</title>
<jsp:useBean id="notificationService"
	class="com.courses.services.NotificationService"></jsp:useBean>
</head>
<body>
	<div id="root">
		<!-- Header -->
		<jsp:include page="../partials/header.jsp" />
		<!-- Body -->
		<main id="main">
			<div class="container">
				<div class="grid">
					<div class="grid_row">
						<div class="grid_column_1">
							<jsp:include page="./sidebar.jsp" />
						</div>
						<div class="grid_column_3">
							<div class="topic_registration">
								<ion-icon name="home-outline"></ion-icon>
								<h3>TRANG CỦA BẠN</h3>
							</div>
							<!-- avai -->
							<c:if test="${notifications.size() != 0}">
								<div class="topic_registration-detail">
									<div class="group_topic_registration-to-manage">
										<table>
											<tr>
												<th width="30%">Tiêu đề</th>
												<th width="30%">Người gửi</th>
												<th width="30%">Ngày gửi</th>
											</tr>
										</table>
									</div>
									<c:forEach var="item" items="${notifications}">
										<div class="group_topic_registration-to-manage">
											<c:choose>
												<c:when test="${item.getStatus() == 0}">
													<table>
														<tr>
															<th width="30%"><a
																href="#${item.getNotificationId()}"
																class="bold_content highlight_content">${item.getNotificationTitle()}</a>
															</th>
															<th width="30%" class="bold_content">${item.getPerson1().getFullName()}</th>
															<th width="30%" class="bold_content">${item.getTime()}</th>
														</tr>
													</table>
												</c:when>
												<c:otherwise>
													<table>
														<tr>
															<th width="30%"><a
																href="#${item.getNotificationId()}"
																class="highlight_content">${item.getNotificationTitle()}</a>
															</th>
															<th width="30%">${item.getPerson1().getFullName()}</th>
															<th width="30%">${item.getTime()}</th>
														</tr>
													</table>
												</c:otherwise>
											</c:choose>
											<!-- =============== -->
											<div id="${item.getNotificationId()}" class="overlay">
												<div class="popup">
													<h2>${item.getNotificationTitle()}</h2>
													<a class="close"
														href="<%=context%>/student/show-detail-notification?notification_id=${item.getNotificationId()}">&times;</a>
													<div class="content">
														<b>Time:</b> ${item.getTime()}
													</div>
													<div class="content">
														<b>From:</b> ${item.getPerson1().getEmail()}
													</div>
													<div class="content">
														<b>To:</b> ${item.getPerson2().getEmail()}
													</div>
													<div class="content">
														<b>Content:</b> ${item.getContent()}
													</div>
												</div>
												<!-- =============== -->
											</div>
										</div>
									</c:forEach>
								</div>
							</c:if>
							<c:if test="${notifications.size() == 0}">
								<p class="topic_registration-notification highlight_content">Không
									có thông báo</p>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</main>
		<input id="registrationPeriodForTeacherStatus"
			value="${sessionScope.registrationPeriodForTeacherStatus}" hidden="true" />
		<!-- Modal -->
		<jsp:include page="../partials/logoutModal.jsp"></jsp:include>
		<!-- Footer -->
		<jsp:include page="../partials/footer.jsp" />
		<jsp:include page="../partials/script.jsp" />
		<script>
			const registrationPeriodForTeacherStatus = $(
					'#registrationPeriodForTeacherStatus').val();

			if (registrationPeriodForTeacherStatus === 'fail') {
				Swal
						.fire(
								"Thông báo!",
								"Không nằm trong khoảng thời gian đăng ký dành cho sinh viên!!",
								"info");
			} else if (registrationPeriodForTeacherStatus === 'error') {
				Swal.fire("Thông báo!",
						"Đã có lỗi xảy ra vui lòng thử lại sau", "error");
			}
		</script>
	</div>
</body>
</html>
