<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <% String context =
request.getContextPath(); %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="../partials/head.jsp"></jsp:include>
    <title>Groups | Topic Registration Admin</title>
  </head>
  <body>
    <div class="wrapper">
      <jsp:include page="../partials/sidebar.jsp"></jsp:include>
      <div id="body" class="active">
        <!-- navbar navigation component -->
        <jsp:include page="../partials/navigation.jsp"></jsp:include>
        <!-- end of navbar navigation -->
        <div class="content">
          <div class="content">
            <div class="container">
              <div class="page-title">
                <h3>Group Manage</h3>
              </div>
              <div class="box box-primary">
                <div class="box-body">
                  <table
                    width="100%"
                    class="table table-hover"
                    id="dataTables-example"
                  >
                    <thead>
                      <tr>
                        <th>Group ID</th>
                        <th>Topic Name</th>
                        <th>No of Member</th>
                        <th></th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="item" items="${groups}">
                        <tr>
                          <td>${item.getGroupId()}</td>
                          <td>${item.getTopic().getTopicName()}</td>
                          <td>
                          <c:if test="${item.getTopic() != null}">
                            ${item.getCurrentNumber()}/${item.getTopic().getMaxMoMember()}
                          </c:if>
                          </td>
                          <td class="text-end">
                            <a
                              href="<%=context%>/admin/group/edit?id=${item.groupId}"
                              class="btn btn-outline-info btn-rounded"
                              ><i class="fas fa-pen"></i
                            ></a>
                            <button
                              type="button"
                              data-bs-toggle="modal"
                              data-bs-target="#modalConfirmDeleteGroup"
                              class="btn btn-outline-danger btn-rounded"
                            >
                              <i class="fas fa-trash"></i>
                            </button>
                          </td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>
                <div class="row">
                  <div class="col-sm-12 col-md-5">
                    <div
                      class="dataTables_info"
                      id="dataTables-example_info"
                      role="status"
                      aria-live="polite"
                    >
                      Showing 1 to 7 of 7 entries
                    </div>
                  </div>
                  <div class="col-sm-12 col-md-7">
                    <div
                      class="dataTables_paginate paging_simple_numbers"
                      id="dataTables-example_paginate"
                    >
                      <ul class="pagination justify-content-end mx-2">
                        <li
                          class="paginate_button page-item previous disabled"
                          id="dataTables-example_previous"
                        >
                          <a
                            href="#"
                            aria-controls="dataTables-example"
                            data-dt-idx="0"
                            tabindex="0"
                            class="page-link"
                            >Previous</a
                          >
                        </li>
                        <li class="paginate_button page-item active">
                          <a
                            href="#"
                            aria-controls="dataTables-example"
                            data-dt-idx="1"
                            tabindex="0"
                            class="page-link"
                            >1</a
                          >
                        </li>
                        <li class="paginate_button page-item">
                          <a
                            href="#"
                            aria-controls="dataTables-example"
                            data-dt-idx="1"
                            tabindex="0"
                            class="page-link"
                            >2</a
                          >
                        </li>
                        <li
                          class="paginate_button page-item next disabled"
                          id="dataTables-example_next"
                        >
                          <a
                            href="#"
                            aria-controls="dataTables-example"
                            data-dt-idx="2"
                            tabindex="0"
                            class="page-link"
                            >Next</a
                          >
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <jsp:include page="./modalConfirmDeleteGroup.jsp"></jsp:include>
      </div>
    </div>
    <jsp:include page="../partials/tail.jsp"></jsp:include>
  </body>
</html>
