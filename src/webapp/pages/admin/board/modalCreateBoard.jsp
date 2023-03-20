<%@page import="com.courses.utils.helper.RandomUtils"%>
<%@page import="com.courses.utils.constants.GenderConstants"%>
<%@page import="com.courses.utils.constants.RoleConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String type = (String) request.getAttribute("type");
String title = "";
String context = request.getContextPath();
%>

<div class="modal fade" id="modalCreateRP" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true"
	data-bs-backdrop="static">
	<div class="modal-dialog modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalCreateRP">
					<i class="fas fa-clock"></i> Create Board
				</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<form action="<%=context %>/admin/boards/create" method="POST">
				<div class="modal-body">
					<div class="col-md-12">
						<!-- 
						<div class="mb-3">
							<label for="site-title" class="form-label">Board ID</label> <input
								type="text" name="boardId" class="form-control"
								value="BO<%=RandomUtils.randomId()%>" readonly="readonly"
								required="required" />
						</div>
						 -->
						<div class="mb-3">
							<label for="site-title" class="form-label">Board name</label> <input
								type="text" name="boardName" class="form-control"
								required="required" />
						</div>
						<div class="mb-3">
							<label for="site-title" class="form-label">No of member</label> <input
								type="number" name="noOfMember" class="form-control" min="0"
								max="10" />
						</div>

						<div class="mb-3">
							<label for="site-title" class="form-label">Description</label>
							<textarea class="form-control" name="description" rows="4"
								required="required"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Save changes</button>
				</div>
			</form>
		</div>
	</div>
</div>