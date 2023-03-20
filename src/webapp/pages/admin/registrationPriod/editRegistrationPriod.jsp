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
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.2.0/css/datepicker.min.css"
	rel="stylesheet">
<style>
.ui-datepicker-calendar {
	display: none;
}
</style>
<title>Edit Registration Period | Topic Registration Administration</title>
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
						<h3>Edit Registration Period</h3>
					</div>
					<div class="box box-primary">
						<div class="box-body">
							<jsp:include page="./navTop.jsp"></jsp:include>
							<div class="tab-content" id="myTabContent">
								<jsp:include page="./generalPanel.jsp"></jsp:include>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../partials/tail.jsp"></jsp:include>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.min.js"></script>
	<script>
		$('.date-own').datepicker({
			format : "yyyy",
			viewMode : "years",
			minViewMode : "years",
			autoclose : true
		});
		$('.datetime').datepicker();
	</script>
</body>
</html>