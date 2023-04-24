<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.courses.dao.TopicDAO"%>


<%
String context = request.getContextPath();
TopicDAO td = new TopicDAO();
String check = (String)request.getAttribute("isAdded");

%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../partials/head.jsp" />
<title>Trang chủ - Đăng ký đề tài</title>
<style>
.form-label {
	color: var(--text-blue-color);
}
</style>
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
								<h3>Thêm đề tài</h3>
							</div>
							<div class="form-content mt-4 d-flex justify-content-center">
								<div class="col col-lg-6">
									<form class="<%=context%>/teacher/topic-manage/add"
										method="post">
										<input type="text" name="topicId" class="form-control"
											value="<%=td.randomId()%>" hidden="true" /> <input type="text"
											name="teacherId" class="form-control"
											value="${teacher.teacherId}" hidden="true" />
										<div class="mb-3">
											<label for="site-title" class="form-label form_label">Đợt
												đăng ký </label> <input type="text" name="registrationPeriodId"
												class="form-control form-control-lg"
												value="${period.registrationPeriodId}" disabled />
										</div>
										<div class="mb-3">
											<label for="site-title" class="form-label">Chuyên
												ngành </label> <input type="text" name="registrationPeriodName"
												class="form-control form-control-lg"
												value="${teacher.major.majorId}" disabled />
										</div>

										<div class="mb-3">
											<label for="site-title" class="form-label">Tên đề tài
											</label> <input type="text" name="topicName"
												class="form-control form-control-lg" />
										</div>

										<div class="mb-3">
											<label for="site-title" class="form-label">Số lượng
												thành viên </label> <input type="number" min="2" max="3"
												name="numberOfMember" class="form-control form-control-lg" />
										</div>
										<div class="mb-3">
											<label for="site-title" class="form-label">Mô tả</label>
											<textarea class="form-control" name="topicDescription"
												rows="4"></textarea>
										</div>
										<div class="mb-3 d-flex flex-row-reverse">
											<button class="btn btn-primary btn-lg" type="submit">
												<i class="fas fa-check"></i> Thêm
											</button>
										</div>
									</form>

								</div>

							</div>

						</div>
					</div>
				</div>
			</div>
		</main>
		<!-- Modal -->
		<jsp:include page="../partials/logoutModal.jsp" />
		<jsp:include page="./modalAddTopicSuccess.jsp" />
		<!-- Footer -->
		<jsp:include page="../partials/footer.jsp" />
	</div>
	<%
	if (check != null) {
	%>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#addTopicSuccess").modal('show');
		});
	</script>
	<%
	}
	%>
</body>
</html>