<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String context = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../partials/head.jsp"></jsp:include>
<title>Groups | Topic Registration Admin</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../partials/sidebar.jsp"></jsp:include>
		<div id="body" class="active">
			<!-- navbar navigation component -->
			<jsp:include page="../partials/navigation.jsp"></jsp:include>
			<!-- end of navbar navigation -->
			<div class="content">
				<div class="container">
					<div class="page-title">
						<h3>Groups</h3>
					</div>
					<div class="box box-primary">
						<div class="box-body">
							<ul class="nav nav-tabs" id="myTab" role="tablist">
								<li class="nav-item"><a class="nav-link active"
									id="general-tab" data-bs-toggle="tab" href="#general"
									role="tab" aria-controls="general" aria-selected="true">General</a>
								</li>
								<li class="nav-item"><a class="nav-link" id="system-tab"
									data-bs-toggle="tab" href="#system" role="tab"
									aria-controls="system" aria-selected="false">Members</a></li>
							</ul>
							<div class="tab-content" id="myTabContent">
								<div class="tab-pane fade active show" id="general"
									role="tabpanel" aria-labelledby="general-tab">
									<div class="col-md-6">
										<div class="mb-3">
											<label for="site-title" class="form-label">Group ID</label> <input
												type="text" name="site_title" class="form-control" disabled>
										</div>
										<div class="mb-3">
											<label for="site-description" class="form-label">Status</label>
											<select name="timezone" class="form-select">
												<option value="">Select your status</option>
												<option value="">approved</option>
												<option value="">Pending</option>
											</select>
										</div>
										<div class="mb-3">
											<label for="site-description" class="form-label">Active</label>
											<select name="timezone" class="form-select">
												<option value="">Select your active</option>
												<option value="">Activated</option>
												<option value="">Pending</option>
											</select>
										</div>
										<div class="mb-3">
											<label for="site-description" class="form-label">Topic</label>
											<select name="timezone" class="form-select">
												<option value="">Select topic</option>
											</select>
										</div>
										<div class="mb-3">
											<label for="site-title" class="form-label">Descripton</label>
											<textarea class="form-control" name="google_analytics_code"
												rows="4"></textarea>
										</div>
										<div class="mb-3 d-flex justify-content-between">
											<button class="btn btn-success" type="submit">
												<i class="fas fa-chevron-left"></i> Back
											</button>
											<button class="btn btn-success" type="submit">
												<i class="fas fa-check"></i> Save
											</button>
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="system" role="tabpanel"
									aria-labelledby="system-tab">
									<div class="col-md-6">
										<div class="mb-3">
											<label for="site-title" class="form-label">Status</label> <input
												type="text" name="site_title" class="form-control" disabled
												value="Full">
										</div>
										<div class="mb-3">
											<label for="site-title" class="form-label">Leader</label> <input
												type="text" name="site_title" class="form-control" disabled
												value="Phạm Nguyễn Nhựt Trường">
										</div>
										<c:forEach begin="1" end="2" var="item">
											<div class="mb-3">
												<label for="site-description" class="form-label">Member ${item}
													</label> <select name="timezone" class="form-select">
													<option value="">Select member</option>
												</select>
											</div>
										</c:forEach>
										<div class="mb-3 d-flex justify-content-between">
											<button class="btn btn-success" type="submit">
												<i class="fas fa-chevron-left"></i> Back
											</button>
											<button class="btn btn-success" type="submit">
												<i class="fas fa-check"></i> Save
											</button>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../partials/tail.jsp"></jsp:include>
</body>
</html>