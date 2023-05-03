<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="tab-pane fade active show" id="general" role="tabpanel"
	aria-labelledby="general-tab">
	<div class="col-md-6">
		<form method="POST" action="update-general">
			<input type="text" name="personId" class="form-control"
				value="${user.getPerson().getPersonId()}" hidden="true" /> <input
				type="text" name="type" class="form-control" value="${type}" hidden="true" />
			<div class="mb-3">
				<label for="site-title" class="form-label">Full name</label> <input
					type="text" name="fullname" class="form-control"
					value="${user.getPerson().getFullName()}" />
			</div>
			<div class="mb-3">
				<label for="site-title" class="form-label">Address</label> <input
					type="text" name="address" class="form-control"
					value="${user.getPerson().getAddress()}" />
			</div>
			<div class="mb-3">
				<label for="site-description" class="form-label">Gender</label> <select
					name="gender" class="form-select">
					<option value="0">male</option>
					<option value="1">female</option>
				</select>
			</div>
			<div class="mb-3">
				<label for="site-title" class="form-label">Phonenumber</label> <input
					type="text" name="phonenumber" class="form-control"
					value="${user.getPerson().getPhonenumber()}" />
			</div>
			<div class="mb-3">
				<label for="site-title" class="form-label">Descripton</label>
				<textarea class="form-control" name="descripton" rows="4">${user.getPerson().getDescription()}</textarea>
			</div>
			<jsp:include page="./navPanelBottom.jsp"></jsp:include>
		</form>
	</div>
</div>