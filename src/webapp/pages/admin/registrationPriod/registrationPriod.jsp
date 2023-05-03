<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.courses.utils.constants.RoleConstants"%>
<%
String context = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../partials/head.jsp"></jsp:include>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.2.0/css/datepicker.min.css"
	rel="stylesheet">
<style>
.ui-datepicker-calendar {
	display: none;
}
</style>
<title>Registration Priods For Teacher | Topic Registration
	Admin</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../partials/sidebar.jsp"></jsp:include>
		<div id="body" class="active">
			<!-- navbar navigation component -->
			<jsp:include page="../partials/navigation.jsp"></jsp:include>
			<!-- end of navbar navigation -->
			<div class="content">
				<div class="content">
					<div class="container">
						<div class="page-title">
							<h3>
								Registration Priods For ${type.substring(0, 1).toUpperCase()}${type.substring(1)}
								Manage <a type="button"
									href="<%=context %>/admin/registration-priods/is-deleted/?type=${type}"
									class="btn btn-sm btn-outline-primary float-end"> <i
									class="fas fa-clock"></i> Restore
								</a>
								<button type="button" data-bs-toggle="modal"
									data-bs-target="#modalCreateRP"
									class="btn btn-sm btn-outline-primary float-end me-3">
									<i class="fas fa-clock"></i> Create New
								</button>
							</h3>
						</div>
						<div class="box box-primary">
							<c:if test="${registrationPeriods.size() > 0}">
								<div class="box-body">
									<table width="100%" class="table table-hover"
										id="dataTables-example">
										<thead>
											<tr>
												<th>ID</th>
												<th>Name</th>
												<th>Semester</th>
												<th>School Year</th>
												<th>Open Date</th>
												<th>Close Date</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="item" items="${registrationPeriods}">
												<tr>
													<td>${item.getRegistrationPeriodId()}</td>
													<td>${item.getRegistrationPeriodName()}</td>
													<td>${item.getSemeter()}</td>
													<td>${item.getSchoolYear()}</td>
													<td>${item.getOpenDate()}</td>
													<td>${item.getCloseDate()}</td>
													<td class="text-end"><a
														href="<%=context%>/admin/registration-priods/edit/?type=${type}&id=${item.getRegistrationPeriodId()}"
														class="btn btn-outline-info btn-rounded"> <i
															class="fas fa-pen"></i>
													</a>

														<button type="button" data-bs-toggle="modal"
															data-bs-target="#modalConfirmDeleteRP"
															class="btn btn-outline-danger btn-rounded">
															<i class="fas fa-trash"></i>
														</button> <jsp:include page="modalConfirmDeleteRP.jsp">
															<jsp:param value="${item.getRegistrationPeriodId()}"
																name="registrationPeriodId" />
														</jsp:include>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</c:if>

							<c:if test="${registrationPeriodsIsDeleted.size() > 0}">
								<table width="100%" class="table table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>ID</th>
											<th>Name</th>
											<th>Semester</th>
											<th>School Year</th>
											<th>Open Date</th>
											<th>Close Date</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${registrationPeriodsIsDeleted}">
											<tr>
												<td>${item.getRegistrationPeriodId()}</td>
												<td>${item.getRegistrationPeriodName()}</td>
												<td>${item.getSemeter()}</td>
												<td>${item.getSchoolYear()}</td>
												<td>${item.getOpenDate()}</td>
												<td>${item.getCloseDate()}</td>
												<td class="text-end"><a
													href="<%=context%>/admin/registration-priods/is-deleted/restore/?type=${type}&id=${item.getRegistrationPeriodId()}"
													class="btn btn-outline-primary btn-rounded"> <i
														class="fas fa-trash-restore"></i>
												</a>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:if>
							<div class="row">
								<div class="col-sm-12 col-md-5">
									<div class="dataTables_info" id="dataTables-example_info"
										role="status" aria-live="polite">Showing 1 to 7 of 7
										entries</div>
								</div>
								<div class="col-sm-12 col-md-7">
									<div class="dataTables_paginate paging_simple_numbers"
										id="dataTables-example_paginate">
										<ul class="pagination justify-content-end mx-2">
											<li class="paginate_button page-item previous disabled"
												id="dataTables-example_previous"><a href="#"
												aria-controls="dataTables-example" data-dt-idx="0"
												tabindex="0" class="page-link">Previous</a></li>
											<li class="paginate_button page-item active"><a href="#"
												aria-controls="dataTables-example" data-dt-idx="1"
												tabindex="0" class="page-link">1</a></li>
											<li class="paginate_button page-item"><a href="#"
												aria-controls="dataTables-example" data-dt-idx="1"
												tabindex="0" class="page-link">2</a></li>
											<li class="paginate_button page-item next disabled"
												id="dataTables-example_next"><a href="#"
												aria-controls="dataTables-example" data-dt-idx="2"
												tabindex="0" class="page-link">Next</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<input type="text" id="isCreateRegistrationPeriod"
		value="${sessionScope.isCreateRegistrationPeriod}" hidden="true" />
	<input type="text" id="isRegistrationPeriodUpdate"
		value="${sessionScope.isRegistrationPeriodUpdate}" hidden="true" />
	<input type="text" id="isSoftDeleteRegistrationPeriod"
		value="${sessionScope.isSoftDeleteRegistrationPeriod}" hidden="true" />
	<input type="text" id="isRestoreRegistrationPeriod"
		value="${sessionScope.isRestoreRegistrationPeriod}" hidden="true" />

	<jsp:include page="./modalCreateRP.jsp"></jsp:include>
	<jsp:include page="./modalConfirmDeleteRP.jsp"></jsp:include>
	<jsp:include page="../partials/tail.jsp"></jsp:include>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script>
		$('.date-own').datepicker({
			format : "yyyy",
			viewMode : "years",
			minViewMode : "years",
			autoclose : true
		});
		$('.datetime').datepicker();

		const isCreateRegistrationPeriod = $('#isCreateRegistrationPeriod')
				.val();
		const isRegistrationPeriodUpdate = $('#isRegistrationPeriodUpdate')
				.val();
		const isSoftDeleteRegistrationPeriod = $(
				'#isSoftDeleteRegistrationPeriod').val();
		const isRestoreRegistrationPeriod = $('#isRestoreRegistrationPeriod')
				.val();

		if (isCreateRegistrationPeriod === 'FAILED') {
			swal(
					"Thông báo!",
					"Thêm mới thời gian đăng kí thất bại, hãy kiểm tra thời gian đăng ký cho giảng viên, số lượng thời gian đăng ký trong một năm học",
					"error");
		} else if (isCreateRegistrationPeriod === 'SUCCESS') {
			swal("Thông báo!", "Thêm mới thời gian đăng kí thành công",
					"success");
		}

		if (isRegistrationPeriodUpdate === 'FAILED') {
			swal(
					"Thông báo!",
					"Cập nhật thời gian đăng kí thất bại, hiện tại đã thời gian đăng ký được mở không thể mở cùng lúc 2 khung thời gian đăng ký, vui lòng kiểm tra lại!!",
					"error");
		} else if (isRegistrationPeriodUpdate === 'SUCCESS') {
			swal("Thông báo!", "Cập nhật thời gian đăng kí thành công",
					"success");
		}

		if (isSoftDeleteRegistrationPeriod === 'FAILED') {
			swal("Thông báo!", "Xóa thời gian đăng kí thất bại", "error");
		} else if (isSoftDeleteRegistrationPeriod === 'SUCCESS') {
			swal("Thông báo!", "Xóa thời gian đăng kí thành công", "success");
		}

		if (isRestoreRegistrationPeriod === 'FAILED') {
			swal("Thông báo!", "Khôi phục thời gian đăng kí đã xóa thất bại",
					"error");
		} else if (isRestoreRegistrationPeriod === 'SUCCESS') {
			swal("Thông báo!", "Khôi phục thời gian đăng kí đã xóa thành công",
					"success");
		}
	</script>
</body>
</html>