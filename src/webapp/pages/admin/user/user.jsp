<%@page import="com.courses.utils.constants.RoleConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String context = request.getContextPath();
String type = (String) request.getAttribute("type");
String title = type.substring(0, 1).toUpperCase() + type.substring(1);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../partials/head.jsp"></jsp:include>
<title><%=title%> | Topic Registration Admin</title>
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
							<h3 class="d-flex justify-content-between">
								<span><%=title%> Manage</span>
								<div class="d-flex gap-2">
									<button type="button" data-bs-toggle="modal"
										data-bs-target="#modalCreateUser"
										class="btn btn-sm btn-outline-primary float-end">
										<i class="fas fa-user-plus"></i> Create New
									</button>
									<a href="<%=context%>/admin/users/trash/?type=<%=type%>"
										class="btn btn-sm btn-outline-primary"> <i
										class="fas fa-trash"></i> Trash
									</a>
								</div>

							</h3>
						</div>
						<div class="box box-primary">
							<div class="box-body">
								<c:choose>
									<c:when test="${users.size() != 0}">
										<table width="100%" class="table table-hover"
											id="dataTables-example">
											<thead>
												<tr>
													<th>User ID</th>
													<th>Name</th>
													<th>Email</th>
													<th>Role</th>
													<th>Type</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${users}">
													<c:if test="${item.getPerson().getIsDeleted() == 0}">
														<tr>
															<td>${item.getPerson().getPersonId()}</td>
															<td>${item.getPerson().getFullName()}</td>
															<td>${item.getPerson().getEmail()}</td>
															<td>${item.getPerson().getRole()}</td>
															<td>Normal</td>
															<td class="text-end">
															<c:choose>
																	<c:when test="${type == RoleConstants.ADMIN}">
																		<a
																			href="<%=context%>/admin/users/edit/?type=${type}&id=${item.getAdminId()}"
																			class="btn btn-outline-info btn-rounded"><i
																			class="fas fa-pen"></i></a>
																	</c:when>
																	<c:when test="${type == RoleConstants.TEACHER}">
																		<a
																			href="<%=context%>/admin/users/edit/?type=${type}&id=${item.getTeacherId()}"
																			class="btn btn-outline-info btn-rounded"><i
																			class="fas fa-pen"></i></a>
																	</c:when>
																	<c:otherwise>
																		<a
																			href="<%=context%>/admin/users/edit/?type=${type}&id=${item.getStudentId()}"
																			class="btn btn-outline-info btn-rounded"><i
																			class="fas fa-pen"></i></a>
																	</c:otherwise>
																</c:choose>
																<button type="button" data-bs-toggle="modal"
																	data-bs-target="#exampleModal"
																	data-bs-id="${item.getPerson().getPersonId()}"
																	class="btn btn-outline-danger btn-rounded btn-delete">
																	<i class="fas fa-trash"></i>
																</button>
															</td>
														</tr>

													</c:if>
												</c:forEach>
											</tbody>
										</table>
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
														<li class="paginate_button page-item active"><a
															href="#" aria-controls="dataTables-example"
															data-dt-idx="1" tabindex="0" class="page-link">1</a></li>
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
									</c:when>
									<c:otherwise>
										<h1>No Data</h1>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="./modalConfirmDeleteUser.jsp"></jsp:include>
			<jsp:include page="./modalCreateUser.jsp"></jsp:include>
		</div>
	</div>
	
	<input type="text" id="isRecoveryAll"
		value="${sessionScope.isRecoveryAll}" hidden/>	
		
	<jsp:include page="../partials/tail.jsp"></jsp:include>
	<script type="text/javascript"
		src="<%=context%>/assets/js/softDeleteUser.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script type="text/javascript">
		const isRecoveryAll = $('#isRecoveryAll').val();
		
		if (isRecoveryAll === 'FAILED') {
			swal("Thông báo!", "Khôi phục tất cả users đã xóa thất bại", "error");
		} else if (isRecoveryAll === 'SUCCESS') {
			swal("Thông báo!", "Khôi phục tất cả users đã xóa	 thành công",
					"success");
		}
	</script>
</body>
</html>