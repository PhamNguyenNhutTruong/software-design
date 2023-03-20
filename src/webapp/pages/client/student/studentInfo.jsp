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
							<jsp:include page="./sidebar.jsp" />
				</div>
                <div class="grid_column_3">
                    <div class="topic_registration">
                        <ion-icon name="person-outline"></ion-icon>
                        <h3>THÔNG TIN CÁ NHÂN</h3>
                    </div>
                    <!-- student info -->
                    <div class="info__student--container">
                        <div class="info_student">
                            <h3 class="title backgroud__info_student">THÔNG TIN SINH VIÊN</h3>
                            <ul class="list__info">
                                <li class="info__item">
                                    <h3>Mã số sinh viên:</h3>
                                    <h3>${student.studentId}</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Họ và tên:</h3>
                                    <h3>${person.fullName}</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Giới tính:</h3>
                                    <h3>${person.getGender() == 1 ? 'Nam' : 'Nữ'}</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Chuyên ngành:</h3>
                                    <h3>${student.major.majorName}</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Tôn giáo:</h3>
                                    <h3>Không</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Khu vực:</h3>
                                    <h3>Khu vực 2 nông thôn</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Địa chỉ:</h3>
                                    <h3>${person.getAddress() }</h3>
                                </li>
                            </ul>
                            <h3 class="title backgroud__info_courses">THÔNG TIN KHÓA HỌC</h3>
                            <ul class="list__info">
                                <li class="info__item">
                                    <h3>Khóa học:</h3>
                                    <h3>Khóa 2020</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Niên khóa:</h3>
                                    <h3>${student.schoolYear}</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Năm nhập học:</h3>
                                    <h3>2020</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Năm hết hạn đào tạo:</h3>
                                    <h3>2020 - 2028</h3> 
                                </li>
                                <li class="info__item">
                                    <h3>Chương trình đào tạo:</h3>
                                    <h3>20110</h3>
                                </li>
                                <li class="info__item">
                                    <h3>Lớp sinh viên:</h3>
                                    <h3>201103B</h3>
                                </li>
                            </ul>
                        </div>
                        <div class="contact__student">
                            <div class="img_container">
                            <!-- 
                                <img src="https://cdn.tgdd.vn/Files/2019/12/21/1227869/tu-van-chon-mua-ong-kinh-lens-may-anh-de-chup-anh-chan-dung-xoa-phong-14.jpg" alt="">
                             -->
                             <img src="https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg?20200913095930" alt="">
                            </div>
                            <h3 class="title backgroud__info_contact">THÔNG TIN LIÊN LẠC</h3>
                            <h3 class="description">* Thông tin liên lạc của sinh viên:</h3>
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
                                    <h3>Ghi chú:</h3>
                                    <h3>${person.description}</h3>
                                </li>
                            </ul>
                            <h3 class="description">* Thông tin liên lạc người thân của sinh viên (khi không liên lạc được với sinh viên):</h3>
                            <ul class="list__info">
                                <li class="info__item">
                                    <h3>Họ tên:</h3>
                                    <h3></h3>
                                </li>
                                <li class="info__item">
                                    <h3>Điện thoại:</h3>
                                    <h3></h3>
                                </li>
                                <li class="info__item">
                                    <h3>Địa chỉ:</h3>
                                    <h3></h3>
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