<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="tab-pane fade show" id="account" role="tabpanel"
	aria-labelledby="account-tab">
	<form method="POST" action="update-account">
		<div class="col-md-6">
			<input type="text" name="personId" hidden
				value="${user.getPerson().getPersonId()}"> <input
				type="text" name="type" value="${type}" hidden />
			<div class="mb-3">
				<label for="site-title" class="form-label">Username</label>
				<c:choose>
					<c:when test="${account.username == null}">
						<input type="text" name="username" class="form-control">
					</c:when>
					<c:otherwise>
						<input type="text" name="username" class="form-control" disabled
							value="${account.username}" />
					</c:otherwise>
				</c:choose>
			</div>
			<div class="mb-3">
				<label for="site-title" class="form-label">New password</label> <input
					type="password" name="password" class="form-control" />
			</div>
			<div class="mb-3">
				<label for="site-title" class="form-label">Confirm password</label>
				<input type="password" name="confirmPassword" class="form-control" />
			</div>
			<div class="mb-3">
				<label for="site-title" class="form-label">Description</label>
				<textarea class="form-control" name="description" rows="4"></textarea>
			</div>
			<jsp:include page="./navPanelBottom.jsp"></jsp:include>
		</div>
	</form>
</div>