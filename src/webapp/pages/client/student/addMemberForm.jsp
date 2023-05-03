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
<title>Trang chủ - Đăng ký thành viên</title>
</head>
<body>
	<jsp:include page="../partials/header.jsp" />
	<div class="form__contain">
		<form action="<%=context %>/add-member-to-group" method="get">
			<h1>Register Group Student</h1>
			<p>Please fill in this form to create an group student for
				registration topic.</p>
			<hr>

			<label for="studentId"><b>Student ID</b></label> <input type="text"
				placeholder="Enter Student ID" name="studentId" id="studentId"
				required><br>
			<hr>
			<p>
				By creating an account you agree to our <a href="#">Terms &
					Privacy</a>.
			</p>

			<button type="submit" class="registerbutton">Register</button>
			<c:if test="${sessionScope.joinGroups != null}">
				<h3>Danh sách sinh viên xin tham gia vào nhóm</h3>
				<div class="join__group-container-item">
					<table>
						<tr>
							<th width="40%">Họ và tên</th>
							<th width="40%">Email</th>
							<th class="hide_element">Xem chi tiết</th>
						</tr>
					</table>
				</div>
				<c:forEach var="item" items="${sessionScope.joinGroups}">
					<div class="join__group-container-item">
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
		</form>
	</div>
	<jsp:include page="../partials/footer.jsp" />
	<jsp:include page="../partials/logoutModal.jsp"></jsp:include>
	<script type="module"
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>
