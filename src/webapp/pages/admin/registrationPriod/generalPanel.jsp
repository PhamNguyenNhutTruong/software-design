<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="tab-pane fade active show" id="general" role="tabpanel"
	aria-labelledby="general-tab">
	<div class="col-md-6">
		<form method="POST" action="update-period">
			<input type="text" name="registrationPeriodId" class="form-control"
				hidden value="${rp.getRegistrationPeriodId()}" /> <input
				type="text" name="isRegistrationTeacher" class="form-control"
				value="${rp.getIsRegistrationTeacher()}" hidden />

			<div class="mb-3">
				<label for="registrationPeriodName" class="form-label">Name</label>
				<input type="text" name="registrationPeriodName"
					class="form-control" value="${rp.getRegistrationPeriodName()}" />
			</div>
			<div class="mb-3">
				<label for="semeter" class="form-label">Semester</label> <input
					type="text" name="semeter" class="form-control"
					value="${rp.getSemeter()}" />
			</div>
			<div class="mb-3">
				<label for="schoolYear" class="form-label">School Year</label> <input
					type="text" name="schoolYear" class="form-control date-own"
					value="${rp.getSchoolYear()}" />
			</div>
			<div class="mb-3">
				<label for="openDate" class="form-label">Open Date</label>
				<!-- 				
				<input type="text" name="openDate" class="form-control datetime" value="${rp.getOpenDate()}"/>			
				 -->
				<input type="datetime-local" id="openDate" name="openDate"
					class="form-control" value="${rp.getOpenDate()}" />
			</div>
			<div class="mb-3">
				<label for="closeDate" class="form-label">Close Date</label>
				<!-- 
				<input type="text" name="closeDate" class="form-control datetime" value="${rp.getCloseDate()}"/>
				 -->
				<input type="datetime-local" id="closeDate" name="closeDate"
					class="form-control" value="${rp.getCloseDate()}" />
			</div>
			<div class="mb-3">
				<label for="isActive" class="form-label">Active Date</label> <select
					name="isActive" class="form-control">
					<c:forEach items="Pending, Active" var="item" varStatus="vs">
						<c:choose>
							<c:when test="${vs.index == rp.getIsActive()}">
								<option value="${vs.index}" selected>${item}</option>
							</c:when>
							<c:otherwise>
								<option value="${vs.index}">${item}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="mb-3">
				<label for="description" class="form-label">Description</label>
				<textarea class="form-control" name="description" rows="4">${rp.getDescription()}</textarea>
			</div>
			<jsp:include page="./navPanelBottom.jsp"></jsp:include>
		</form>
	</div>
</div>