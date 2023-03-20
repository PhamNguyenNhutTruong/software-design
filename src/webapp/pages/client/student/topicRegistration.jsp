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
<title>Trang chủ - Đăng ký đề tài</title>
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
								<ion-icon name="pencil"></ion-icon>
								<h3>ĐĂNG KÝ ĐỀ TÀI</h3>
							</div>
							<div class="topic_registration-filter">
								<h3 class="${activeButtonUnselected}">
									<a href="<%=context%>/topic-registration/topic-is-selected">Đề tài chưa được đăng ký</a>
								</h3>
								<h3 class="${activeButtonSelected }">
									<a href="<%=context%>/topic-registration/topic-unselected">Đề tài đã được đăng ký</a>
								</h3>
							</div>
							<!-- 
							<c:choose>
								<c:when test="${message == null}">
									<div class="topic_registration-detail">
								<div class="group_topic_registration-to-manage">
									<table>
										<tr>
											<th width="40%">Tên đề tài</th>
											<th width="25%">Giảng viên hướng đẫn</th>
											<th width="25%">Số lượng thành viên</th>
											<th class="hide_element">Xem chi tiết</th>
										</tr>
									</table>
								</div>
								<c:forEach var="item" items="${topics}">
									<div class="group_topic_registration-to-manage">
										<table>
											<tr>
												<th width="40%" class="highlight_content">${item.getTopicName()}</th>
												<th width="25%">${item.getTeacher().getPerson().getFullName()}</th>
												<th width="25%">${item.getMaxMoMember()}</th>
												<th><a href="<%=context%>/topic-registration/register-topic?topic-id=${item.getTopicId()}" class="highlight_content">${item.getIsSelected() == 0 ? 'Đăng ký': ''}</a></th>
											</tr>
										</table>
									</div>
								</c:forEach>
							</div>
								</c:when>
								<c:otherwise>
									<p class="topic_registration-notification highlight_content">${message}</p>
								</c:otherwise>
							</c:choose>
							 -->
							 <c:forEach var="item" items="${topics}">
									<div class="group_topic_registration-to-manage">
										<table>
											<tr>
												<th width="40%" class="highlight_content">${item.getTopicName()}</th>
												<th width="25%">${item.getTeacher().getPerson().getFullName()}</th>
												<th width="25%">${item.getMaxMoMember()}</th>
												<th><a href="<%=context%>/topic-registration/register-topic?topic-id=${item.getTopicId()}" class="highlight_content">${item.getIsSelected() == 0 ? 'Đăng ký': ''}</a></th>
											</tr>
										</table>
									</div>
								</c:forEach>
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
	<input type="text" id="isRegistrationTopic" value="${isRegistrationTopic}" hidden />
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	
	<script>
		const isRegistrationTopic = $('#isRegistrationTopic').val();
		
		if (isRegistrationTopic === 'FAILED') {
			swal("Thông báo!", "Đăng kí đề tài thất bại. Cần đảm bảo các yếu tố sau:\n- Bạn cần phải có nhóm trước khi đăng kí đề tài\n- Bạn chưa đăng kí đề tài nào trước đó\n- Số lượng thành viên phải hợp lệ.", "error");
		} else if (isRegistrationTopic === 'SUCCESS') {
			swal("Thông báo!", "Đăng kí đề tài thành công", "success");
		}
	</script>
</body>
</html>