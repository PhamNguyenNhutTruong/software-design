<%@page import="com.courses.utils.helper.RandomUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="tab-pane fade active show" id="general" role="tabpanel"
	aria-labelledby="general-tab">
	<div class="col-md-6">
		<form method="POST" action="">
			<div class="mb-3">
				<label for="site-title" class="form-label">Board ID</label> <input
					type="text" name="boardId" class="form-control"
					value="${board.getBoardId()}" readonly="readonly"
					required="required" />
			</div>
			<div class="mb-3">
				<label for="site-title" class="form-label">Board name</label> <input
					type="text" name="boardName" class="form-control" value="${board.getBoardName()}"
					required="required" />
			</div>
			<div class="mb-3">
				<label for="site-title" class="form-label">No of member</label> <input
					type="number" name="noOfMember" class="form-control" min="0"value="${board.getNoMember()}"
					max="10" />
			</div>

			<div class="mb-3">
				<label for="site-title" class="form-label">Description</label>
				<textarea class="form-control" name="description" rows="4"
					required="required">${board.getDescription()}</textarea>
			</div>
			<jsp:include page="./navPanelBottom.jsp"></jsp:include>
		</form>
	</div>
</div>