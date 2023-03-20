<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.courses.services.StudentService"%>


<%
String context = request.getContextPath();
StudentService studentService = new StudentService();
%>



<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../partials/head.jsp" />
<title>Giảng viên | Chi tiết đề tài</title>
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
							<h1>${topicName}</h1>
							<div class="topic-group">
								<ion-icon name="receipt-outline"></ion-icon>
								<h3>NHÓM SINH VIÊN ĐĂNG KÝ</h3>
							</div>
							<div class="student-table">
								<c:forEach var="group" items="${groupStudentMap.keySet()}">
									<table id="students">
										<tr class="">
											<th style="width: 20%;">Mã nhóm</th>
											<th style="width: 40%;">Tên thành viên</th>
											<th style="width: 20%;">Mã số sinh viên</th>
											<th style="width: 20%;">Vai trò</th>
										</tr>
										<c:forEach var="student" items="${groupStudentMap.get(group)}"
											varStatus="state">
											<c:choose>
												<c:when test="${state.first}">
													<tr class="">
														<td>${group.groupId}</td>
														<td>${student.person.fullName}</td>
														<td>${student.studentId}</td>
														<c:choose>
															<c:when test="${group.leaderId == student.studentId}">
																<td>Nhóm trưởng</td>
															</c:when>
															<c:otherwise>
																<td>Thành viên</td>
															</c:otherwise>
														</c:choose>

													</tr>
												</c:when>
												<c:otherwise>
													<tr>
														<td></td>
														<td>${student.person.fullName}</td>
														<td>${student.studentId}</td>
														<c:choose>
															<c:when test="${group.leaderId == student.studentId}" >
																<td>Nhóm trưởng</td>
															</c:when>
															<c:otherwise>
																<td>Thành viên</td>
															</c:otherwise>
														</c:choose>
													</tr>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</table>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>

		<!-- Modal -->
		<jsp:include page="../partials/logoutModal.jsp" />
		<!-- Footer -->
		<jsp:include page="../partials/footer.jsp" />

	</div>
</body>
</html>