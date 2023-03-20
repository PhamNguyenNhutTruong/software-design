<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <% String context =
request.getContextPath(); request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="./partials/head.jsp" />
    <title>Login - Đăng ký đề tài</title>
  </head>
  <body>
    <div id="root">
      <!-- Header -->
      <jsp:include page="./partials/header.jsp" />
      <main id="main">
        <div class="grid">
          <div class="form_container">
            <form id="form" action="<%=context%>/login" method="POST">
              <h2 class="login-label">Đăng nhập</h2>
              <div class="login_role">
                <div class="login_role-item">
                  <input
                    type="radio"
                    id="sinhvien"
                    value="student"
                    name="role-account"
                    checked="checked"
                  />
                  <label for="sinhvien">Sinh viên</label>
                </div>
                <div class="login_role-item">
                  <input
                    type="radio"
                    id="giangvien"
                    value="teacher"
                    name="role-account"
                  />
                  <label for="giangvien">Giảng viên</label>
                </div>
                <div class="login_role-item">
                      <input type="radio" id="admin" value="admin" name="role-account" />
                      <label for="admin">Admin</label>
                    </div>
              </div>
              
              <div class="login_input">
                <label for="username" class="login_input-label"
                  >Tên đăng nhập:</label
                >
                <input
                  type="text"
                  id="username"
                  name="username"
                  value="${username}"
                  class="login_input-input login_input-input--boder"
                  required
                />
              </div>
              
              <div class="login_input">
                <label for="password" class="login_input-label"
                  >Mật khẩu:</label
                >
                <input
                  type="password"
                  id="password"
                  name="password"
                  value="${password}"
                  class="login_input-input login_input-input--boder"
                  required
                />
              </div>
              <div><p style="font-size: 1rem; color: red">${error}</p></div>
              <div class="login_input">
                <div class="login_input-label"></div>
                <div class="login_input-input">
                  <div class="login_input-container">
                    <div>
                      <a href="#">Quên mật khẩu</a>
                    </div>
                    <div>
                      <button>Đăng nhập</button>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </main>
      <!-- Footer -->
      <jsp:include page="./partials/footer.jsp" />
    </div>
  </body>
</html>
