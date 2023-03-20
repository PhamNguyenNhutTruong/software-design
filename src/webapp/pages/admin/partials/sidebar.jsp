<%@page import="com.courses.utils.constants.RoleConstants"%>
<%@page import="javax.management.relation.Role"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String context = request.getContextPath();
%>

<nav id="sidebar" class="">
	<div class="sidebar-header">
		<img
			src="https://fit.hcmute.edu.vn/Resources/Images/SubDomain/fit/logo-cntt2021.png"
			alt="bootraper logo" class="app-logo" style="height: 42px" />
	</div>
	<ul class="list-unstyled components text-secondary">
		<li><a href="<%=context%>/admin"><i class="fas fa-home"></i>
				Dashboard</a></li>
		<li><a href="#authmenu" data-bs-toggle="collapse"
			aria-expanded="false" class="dropdown-toggle no-caret-down"><i
				class="fas fa-user-friends"></i> Users</a>
			<ul class="collapse list-unstyled" id="authmenu">
				<li><a
					href="<%=context%>/admin/users/?type=<%=RoleConstants.ADMIN%>"><i
						class="fas fa-user-cog"></i>Admin</a></li>
				<li><a
					href="<%=context%>/admin/users/?type=<%=RoleConstants.TEACHER%>"><i
						class="fas fa-chalkboard-teacher"></i> Teachers</a></li>
				<li><a
					href="<%=context%>/admin/users/?type=<%=RoleConstants.STUDENT%>"><i
						class="fas fa-user-graduate"></i>Students</a></li>
			</ul></li>
		<li><a href="<%=context%>/admin/groups"><i
				class="fas fa-users"></i>Groups</a></li>

		<li><a href="<%=context%>/admin/topics"><i
				class="fas fa-newspaper"></i>Topics</a></li>
		<li><a href="#registration-priods" data-bs-toggle="collapse"
			aria-expanded="false" class="dropdown-toggle no-caret-down"><i
				class="fas fa-clock"></i>Registration Priods</a>
			<ul class="collapse list-unstyled" id="registration-priods">
				<li><a
					href="<%=context%>/admin/registration-priods/?type=<%=RoleConstants.TEACHER%>"><i
						class="fas fa-user-cog"></i>For Teacher</a></li>
				<li><a
					href="<%=context%>/admin/registration-priods/?type=<%=RoleConstants.STUDENT%>"><i
						class="fas fa-chalkboard-teacher"></i>For Student</a></li>
			</ul></li>
		<li><a href="<%=context%>/admin/boards"><i
				class="fas fa-chalkboard-teacher"></i> Boards</a></li>
	</ul>
</nav>
