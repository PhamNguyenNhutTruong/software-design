<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
  <%
  	String context = request.getContextPath();
  %>
 <!-- Modal -->
 <div class="modal fade" id="periodModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" style="font-size: 2rem;">Thông báo</h5>
      </div>
      <div class="modal-body">
        <p>Hiện tại không nằm trong thời gian thêm đề tài</p>
      </div>
      <div class="modal-footer">
      	<form action="<%=context%>/teacher/topic-manage">
      		<button type="submit" class="btn btn-lg btn-secondary" data-bs-dismiss="modal">OK</button>
      	</form>
      </div>
    </div>
  </div>
  </div>

 
 