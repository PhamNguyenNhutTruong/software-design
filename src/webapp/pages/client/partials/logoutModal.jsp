<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
  <%
  	String context = request.getContextPath();
  %>
 <!-- Modal -->
 
<form method="get" action="<%=context%>/logout">
 	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true"="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="exampleModalLabel">Thông báo</h4>
      </div>
      <div class="modal-body">
        Bạn có muốn đăng xuất ?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-lg btn-secondary" data-bs-dismiss="modal">Hủy</button>
        <button type="submit" class="btn btn-lg btn-primary">Xác nhận</button>
      </div>
    </div>
  </div>
</div>
</form> 
 
 