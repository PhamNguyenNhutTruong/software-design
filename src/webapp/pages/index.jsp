<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String context = request.getContextPath();
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="./client/partials/head.jsp" />
<title>HCMUTE - Đăng ký đề tài</title>
</head>
<body>
	<div id="root">
		<!-- Header -->
		<jsp:include page="./client/partials/header.jsp" />
		<main style="margin-top: 200px">
			<h1 style="height: 100vh"></h1>
		</main>
		<!-- Footer -->
		<jsp:include page="./client/partials/footer.jsp" />
		<jsp:include page="./client/partials/logoutModal.jsp" />
	</div>
</body>
</html>