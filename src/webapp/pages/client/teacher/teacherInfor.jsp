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
<title>Thông tin cá nhân</title>
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
                    <div class="topic_registration">
                        <ion-icon name="person-outline"></ion-icon>
                        <h3>THÔNG TIN CÁ NHÂN</h3>
                    </div>
                    <!-- student info -->
                    <div class="info__student--container">
                        <div class="info_student">
                            <h3 class="title backgroud__info_student">THÔNG TIN GIẢNG VIÊN</h3>
                            <ul class="list__info">
                                <li class="info__item">
                                    <h3>Mã số giảng viên:</h3>
                                    <h3>${teacher.teacherId}</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Họ và tên:</h3>
                                    <h3>${person.getFullName() }</h3>
                                </li>
                                 <li class="info__item">
                                    <h3>Chuyên ngành:</h3>
                                    <h3>${teacher.getMajor().getMajorName()}</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Chức vụ:</h3>
                                    <h3>${teacher.isHead == 1 ? 'Giảng viên trưởng bộ môn' : 'Giảng viên'}</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Giới tính:</h3>
                                    <h3>${person.getGender() == 1 ? 'Nam' : 'Nữ'}</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Số CMND/CCCD:</h3>
                                    <h3></h3>
                                </li>
                                <li class="info__item">
                                    <h3>Địa Chỉ</h3>
                                    <h3>${person.getAddress()}</h3>
                                </li>
                            </ul>
                        </div>
                        <div class="contact__student">
                            <div class="img_container">
                                <img src="https://cdn.tgdd.vn/Files/2019/12/21/1227869/tu-van-chon-mua-ong-kinh-lens-may-anh-de-chup-anh-chan-dung-xoa-phong-14.jpg" alt="">
                            </div>
                            <h3 class="title backgroud__info_contact">THÔNG TIN LIÊN LẠC</h3>
                            <h3 class="description">* Thông tin liên lạc của giảng viên:</h3>
                            <ul class="list__info">
                                <li class="info__item">
                                    <h3>Điện thoại:</h3>
                                    <h3>${person.getPhonenumber() }</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Email:</h3>
                                    <h3>${person.getEmail() }</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Địa chỉ:</h3>
                                    <h3>${person.getAddress()}</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Ghi chú:</h3>
                                    <h3>${person.description}</h3>
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
		<jsp:include page="../partials/logoutModal.jsp"></jsp:include>
		<!-- Footer -->
		<jsp:include page="../partials/footer.jsp" />
	</div>
</body>
</html>