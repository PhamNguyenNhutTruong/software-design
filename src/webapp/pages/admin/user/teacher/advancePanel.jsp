<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="tab-pane fade" id="advance" role="tabpanel"
	aria-labelledby="advance-tab">
	<div class="col-md-6">
		<div class="mb-3">
			<label for="site-title" class="form-label">Major</label> <input
				type="text" name="site_title" class="form-control"
				value="${user.getMajor().getMajorName()}">
		</div>
		<div class="mb-3">
			<label for="site-title" class="form-label">Major</label>
			<c:choose>
				<c:when test="${user.getIsHead() == 1}">
					<input type="text" name="site_title" class="form-control" disabled
						value="Trưởng khoa">
				</c:when>
				<c:otherwise>
					<input type="text" name="site_title" class="form-control" disabled
						value="Giảng viên">
				</c:otherwise>
			</c:choose>

		</div>
		<jsp:include page="../navPanelBottom.jsp"></jsp:include>
	</div>
</div>
