<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%
String context = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../partials/head.jsp" />
<title>Giảng viên | Hội đồng của bạn</title>
<style>
	.detail-board{
		text-align: center;
		font-weight: normal;
	}
</style>
</head>
<body>
	<div id="root">
		<!-- Header -->
		<jsp:include page="../partials/header.jsp" />
		<!-- Body -->
		<main>
			<div class="container">

				<div class="grid">
					<div class="grid_row">
						<div class="grid_column_1">
							<jsp:include page="./teacherSidebar.jsp" />
						</div>
						<div class="grid_column_3">
							<div class="topic-group">
								<ion-icon name="receipt-outline"></ion-icon>
								<h3>HỘI ĐỒNG CỦA BẠN</h3>
							</div>
							<div class="student-table">
								<table id="students">
								<tbody>
									<tr class="">
										<th style="width: 20%;">Mã hội đồng</th>
										<th style="width: 40%;">Tên Giảng viên</th>
										<th style="width: 10%;">Mã số giảng viên</th>
										<th style="width: 30%;">Email</th>
									</tr>
									
									<tr class="">
										<td>123456</td>
										<td>Mai Anh Thơ</td>
										<td>TE0000005</td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td>Lê Văn Vinh</td>
										<td>TE0000009</td>
										<td></td>
									</tr>

									<tr>
										<td></td>
										<td>Mai Tuấn Khôi</td>
										<td>TE0000010</td>
										<td></td>
									</tr>

									<tr>
										<td></td>
										<td>Nguyễn Minh Đạo</td>
										<td>TE0000011</td>
										<td></td>
									</tr>

									<tr>
										<td colspan="4" class="detail-board" style="color: black">Xem chi tiết nhóm
											sinh viên hội đồng đánh giá<a style="color: red"> tại đây</a>
										</td>
									</tr>
									</tbody>

								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
		<jsp:include page="../partials/logoutModal.jsp" />
		<!-- Footer -->
		<jsp:include page="../partials/footer.jsp" />
	</div>
</body>
</html>