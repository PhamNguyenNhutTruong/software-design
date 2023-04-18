<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String context = request.getContextPath();
%>

<header>
	<div id="header">
		<div class="nav">
			<div class="image_left"></div>	
			<div class="image_right"></div>	
		</div>
	</div>
	<div class="header_login">
		<div class="grid">
			<c:choose>
				<c:when test="${person != null}">
					<div class="login-name__container">
						<div class="header_login-container">
							<div>${person.fullName}</div>
						</div>
						<div class="header_login-container">
							<button type="button" class="btn btn-primary"
								data-bs-toggle="modal" data-bs-target="#exampleModal">Đăng
								xuất</button>

						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="header_login-container">
						<a href="<%=context%>/login"><button>Đăng nhập</button></a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</header>
