<%@page import="com.courses.utils.helper.RandomUtils"%>
<%@page import="java.util.Random"%>
<%@page import="com.courses.utils.constants.GenderConstants"%>
<%@page import="com.courses.utils.constants.RoleConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String type = (String) request.getAttribute("type");
String title = type.substring(0, 1).toUpperCase() + type.substring(1);
String prefix = "";
if (type.equals(RoleConstants.ADMIN)) {
	prefix = "AD";
} else if (type.equals(RoleConstants.TEACHER)) {
	prefix = "TE";
} else if (type.equals(RoleConstants.STUDENT)) {
	prefix = "ST";
}
%>

<form method="POST" action="create-user">
	<div class="modal fade" id="modalCreateUser" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true"
		data-bs-backdrop="static">
		<div class="modal-dialog modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalCreateUser">
						<i class="fas fa-user-plus"></i> Create
						<%=title%>
					</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="col-md-12">
						<input type="text" name="personId" class="form-control"
							value="PE<%=RandomUtils.randomId()%>" hidden /> <input
							type="text" name="id" class="form-control"
							value="<%=prefix%><%=RandomUtils.randomId()%>" hidden /> <input
							type="text" name="type" class="form-control" value="${type}"
							hidden />
						<div class="mb-3">
							<label for="site-title" class="form-label">Full name</label> <input
								type="text" name="fullname" class="form-control" />
						</div>
						<div class="mb-3">
							<label for="site-title" class="form-label">Email</label> <input
								type="email" name="email" class="form-control" />
						</div>

						<div class="mb-3">
							<label for="site-title" class="form-label">Address</label> <input
								type="text" name="address" class="form-control" />
						</div>
						<div class="mb-3">
							<label for="site-description" class="form-label">gender</label> <select
								name="gender" class="form-select">
								<option value="<%=GenderConstants.MALE%>">Male</option>
								<option value="<%=GenderConstants.FEMALE%>">Female</option>
							</select>
						</div>
						<div class="mb-3">
							<label for="site-title" class="form-label">Phonenumber</label> <input
								type="text" name="phonenumber" class="form-control" />
						</div>
						<div class="mb-3">
							<label for="site-description" class="form-label">Role</label> <select
								name="role" class="form-select">
								<option value="${type}"><%=title%></option>
							</select>
						</div>
						<div class="mb-3">
							<label for="site-title" class="form-label">Description</label>
							<textarea class="form-control" name="description" rows="4"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>
</form>