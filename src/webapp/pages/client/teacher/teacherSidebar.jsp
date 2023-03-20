<%@page import="com.courses.models.Teacher"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.courses.models.Admin"%>
<%@page import="com.courses.models.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="personDAO" class="com.courses.dao.PersonDAO"></jsp:useBean>
<jsp:useBean id="teacherDAO" class="com.courses.dao.TeacherDAO"></jsp:useBean>

<%
String context = request.getContextPath();

String userId = "";
Cookie[] cookies = request.getCookies();
if (cookies != null) {
	for (Cookie cookie : cookies) {
		String cookieName = cookie.getName();
		String cookieValue = cookie.getValue();
		if (cookieName.equals("userIdCookie")) {
	userId = cookieValue;
	break;
		}
	}
}

Person person = personDAO.find(userId);
Map<String, Object> params = new HashMap<>();
params.put("person", person);
Teacher teacher = teacherDAO.findByPerson(params);
%>
<ul class="category-list">
	<li class="category-item"><ion-icon name="home-outline"></ion-icon>
		<a href="<%=context%>/teacher/home" class="category-item__link">Trang
			của bạn</a></li>
	<li class="category-item"><ion-icon name="person-outline"></ion-icon>
		<a href="<%=context%>/teacher/user-profile"
		class="category-item__link">Thông tin giảng viên</a></li>

	<c:if test="${teacher.getIsHead() == 0}">
		<li class="category-item"><ion-icon
				name="calendar-number-outline"></ion-icon> <a
			href="<%=context%>/teacher/topic-manage" class="category-item__link">Quản
				lý đề tài</a></li>
	</c:if>
	
	<c:if test="${teacher.getIsHead() == 1}">
			<li class="category-item"><ion-icon
					name="shield-checkmark-outline"></ion-icon> <a
				href="<%=context%>/teacher/approval" class="category-item__link">Xét
					duyệt đề tài</a></li>
		</c:if>

	<c:choose>
		<c:when test="${teacher.getIsHead() == 1}">
			<li class="category-item"><ion-icon name="bookmarks-outline"></ion-icon>
				<a href="<%=context%>/teacher/board/head" class="category-item__link">Danh
					sách hội đồng</a></li>
		</c:when>
		<c:otherwise>
			<li class="category-item"><ion-icon name="bookmarks-outline"></ion-icon>
				<a href="<%=context%>/teacher/board/normal" class="category-item__link">Hội
					đồng của bạn</a></li>
		</c:otherwise>
	</c:choose>

		
</ul>

