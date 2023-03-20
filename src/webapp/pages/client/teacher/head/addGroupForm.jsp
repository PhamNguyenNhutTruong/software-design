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
<title>Thêm nhóm - Đăng ký đề tài</title>
</head>
<body>
	<div class="form__contain">
		<form action="" method="POST">
			<input name="boardId" value="${boardId}" hidden />
			<h1>Register Group Student</h1>
			<p>Please fill in this form to add an group student for
				registration board.</p>
			<hr>
			<label for="studentId"><b>Group ID</b></label> <input type="text"
				placeholder="Enter Group ID" name="groupId" id="studentId"
				required><br>
			<hr>
			<p>
				By creating an account you agree to our <a href="#">Terms &
					Privacy</a>.
			</p>
			<button type="submit" class="registerbutton">Submit</button>
		</form>
	</div>

</body>
</html>
