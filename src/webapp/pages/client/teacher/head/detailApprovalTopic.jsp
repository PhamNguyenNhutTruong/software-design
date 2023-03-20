<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String context = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../partials/head.jsp" />
<title>Chi tiết đề tài - Đăng ký đề tài</title>
</head>
<body>
	<div id="root">
		<!-- Header -->
		<jsp:include page="../../partials/header.jsp" />
		<!-- Body -->
		<main>
			<div class="container">
				<div class="grid">
					<div class="grid_row">
						<div class="grid_column_1">
							<jsp:include page="../teacherSidebar.jsp" />
						</div>
						<div class="grid_column_3">
							<div class="topic_registration">
								<ion-icon name="person-outline"></ion-icon>
								<h3>CHI TIẾT ĐỀ TÀI</h3>
							</div>
							<!-- student info -->
							<div class="info__student--container">
								<div class="info_student">
									<h3 class="title backgroud__info_student">THÔNG TIN ĐỀ TÀI</h3>
									<ul class="list__info">
										<li class="info__item">
											<h3>Mã đề tài:</h3>
											<h3>${topic.getTopicId()}</h3>
										</li>
										<li class="info__item">
											<h3>Tên đề tài:</h3>
											<h3>${topic.getTopicName() }</h3>
										</li>
										<li class="info__item">
											<h3>Chuyên ngành:</h3>
											<h3>${topic.getMajor().getMajorName()}</h3>
										</li>
										<li class="info__item">
											<h3>Ngày đề xuất:</h3>
											<h3>${person.getGender() == 1 ? 'Nam' : 'Nữ'}</h3>
										</li>
										<li class="info__item">
											<h3>Người đề xuất:</h3>
											<h3>${topic.getTeacher().getPerson().getFullName()}</h3>
										</li>
										<li class="info__item">
											<h3>Số lượng sinh viên:</h3>
											<h3>${topic.getMaxMoMember()}</h3>
										</li>
										<li class="info__item">
											<h3>Mô tả</h3>
											<h3>${topic.getDescription()}</h3>
										</li>
									</ul>
								</div>
								<div class="contact__student">
									<div class="img_container">
										<img
											src="https://cdn.tgdd.vn/Files/2019/12/21/1227869/tu-van-chon-mua-ong-kinh-lens-may-anh-de-chup-anh-chan-dung-xoa-phong-14.jpg"
											alt="">
									</div>
									<h3 class="title backgroud__info_contact">THÔNG TIN LIÊN
										LẠC GIẢNG VIÊN</h3>
									<ul class="list__info">
										<li class="info__item">
											<h3>Điện thoại:</h3>
											<h3>${topic.getTeacher().getPerson().getPhonenumber() }</h3>
										</li>
										<li class="info__item">
											<h3>Email:</h3>
											<h3>${topic.getTeacher().getPerson().getEmail() }</h3>
										</li>
										<li class="info__item">
											<h3>Địa chỉ:</h3>
											<h3>${topic.getTeacher().getPerson().getAddress()}</h3>
										</li>
										<li class="info__item">
											<h3>Ghi chú:</h3>
											<h3>${topic.getTeacher().getPerson().description}</h3>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
		<!-- Modal -->
		<jsp:include page="../../partials/logoutModal.jsp"></jsp:include>
		<!-- Footer -->
		<jsp:include page="../../partials/footer.jsp" />
		<jsp:include page="../../partials/script.jsp" />
	</div>
</body>
</html>