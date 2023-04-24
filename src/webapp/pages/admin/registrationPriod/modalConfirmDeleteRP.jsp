<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
 	String context = request.getContextPath();
 %>
<!-- Modal -->
<form method="get" action="<%=context %>/admin/registration-priods/delete/">
	<div class="modal fade" id="modalConfirmDeleteRP" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true"="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						<i class="fas fa-trash"></i> Delete Student
					</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">This action can't be restored. Do you
					want to continue?</div>
				<input type="text" id="registration-period-id" name="registration-period-id" class="form-control" value="${param.registrationPeriodId}" hidden="true"=""/>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>
</form>