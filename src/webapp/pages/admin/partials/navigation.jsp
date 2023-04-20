<%@page import="com.courses.models.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String context = request.getContextPath();
Person person = (Person)request.getSession().getAttribute("person");
%>
<nav
	class="navbar navbar-expand-lg navbar-white bg-white d-flex justify-content-between">
	<button type="button" id="sidebarCollapse" class="btn btn-light">
		<i class="fas fa-bars"></i><span></span>
	</button>

	<form
		class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
		<div class="input-group">
			<input type="text" class="form-control bg-light border-0 small"
				style="width: 20rem" placeholder="Search for..." aria-label="Search"
				aria-describedby="basic-addon2">
			<div class="input-group-append">
				<button class="btn btn-primary" type="button">
					<i class="fas fa-search fa-sm"></i>
				</button>
			</div>
		</div>
	</form>


	<div class="" id="navbarSupportedContent">
		<ul class="nav navbar-nav ms-auto">
			<li class="nav-item dropdown">
				<div class="nav-dropdown">
					<div id="nav2" class="nav-item nav-link text-secondary dropdown">
					<c:choose>
					   <c:when test="${person != null}">
							<span>Xin chào, ${person.fullName}</span>
					   </c:when>
					   <c:otherwise>
							<span>Xin chào, Admin1</span>
					   </c:otherwise>    
					</c:choose>
						 <img
							class="img-profile rounded-circle dropbtn"
							style="width: 2rem; height: 2rem; object-fit: cover;"
							src="https://images.unsplash.com/photo-1543852786-1cf6624b9987?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80">
						<div class="dropdown-content">
							<a href="#">Account</a> <a href="<%=context%>/logout">Logout</a>
						</div>
					</div>
				</div>
			</li>
		</ul>
	</div>
</nav>