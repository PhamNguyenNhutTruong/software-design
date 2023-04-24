<%@page import="com.courses.utils.helper.RandomUtils"%>
<%@page import="java.util.Random"%>
<%@page import="com.courses.utils.constants.GenderConstants"%>
<%@page import="com.courses.utils.constants.RoleConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form method="POST" action="create-user">
	<div class="modal fade" id="selectSemesterModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true"="true">
		<div class="modal-dialog modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Semesters</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="col-md-12">
						<select class="form-select form-select-sm" size="10"
							aria-label="size 10 select example">
							<option value="1">One</option>
							<option value="2">Two</option>
							<c:forEach begin="1" end="20">
								<option value="3">Three</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</form>