<%@page import="com.courses.utils.helper.RandomUtils"%>
<%@page import="com.courses.utils.constants.GenderConstants"%>
<%@page import="com.courses.utils.constants.RoleConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String type = (String) request.getAttribute("type");
String title = "";
int isTeacher = type.equals(RoleConstants.TEACHER) ? 1 : 0;
%>

<div class="modal fade" id="modalCreateRP" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true"
	data-bs-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalCreateRP">
					<i class="fas fa-clock"></i> Create Schedule
				</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<form action="create" method="post">
				<div class="modal-body">
					<div class="col-md-12">
						<!-- 
						<input type="text" name="registrationPeriodId"
							class="form-control" value="RP<%=RandomUtils.randomId()%>" hidden />
					 -->
						<input type="text" name="isRegistrationTeacher"
							class="form-control" value="<%=isTeacher%>" hidden=""/>
						<div class="mb-3">
							<label for="registrationPeriodName" class="form-label">Name</label>
							<!-- 
							<input type="text" name="registrationPeriodName"
								class="form-control" />
							 -->
							<select name="registrationPeriodName" class="form-select">
								<option value="HK1">HK1</option>
								<option value="HK2">HK2</option>
								<option value="HK3">HK3</option>
							</select>
						</div>
						<div class="mb-3">
							<label for="schoolYear" class="form-label">Year</label> <input
								type="number" min="2000" max="3000" name="schoolYear" class="form-control" />
						</div>
						<div class="mb-3">
							<label for="semester" class="form-label">Semester</label> 
							<select
								name="semester" class="form-select">
								<option value="<%=GenderConstants.HK1%>">HK1</option>
								<option value="<%=GenderConstants.HK2%>">HK2</option>
								<option value="<%=GenderConstants.HK3%>">HK3</option>
							</select>
						</div>
						<div class="mb-3">
							<label for="openDate" class="form-label">Open date</label> 
							<input type="datetime-local" id="openDate" name="openDate" class="form-control"/>
						</div>
						<div class="mb-3">
							<label for="closeDate" class="form-label">Close date</label> 
							<input type="datetime-local" id="closeDate" name="closeDate" class="form-control"/>
						</div>
						<div class="mb-3">
							<label for="description" class="form-label">Description</label>
							<textarea class="form-control" name="description" rows="2"></textarea>
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