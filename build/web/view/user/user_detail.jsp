<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if var="test" test="${sessionScope.customer == null}">
    <jsp:forward page="${root}"></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <title>User detail page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <section style="background-color: #eee;">
                    <div class="container-xxl py-5">
                        <div class="row">
                            <div class="col">
                                <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
                                    <ol class="breadcrumb mb-0">
                                        <li class="breadcrumb-item"><a href="${user}">Home</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">User Profile</li>
                                    </ol>
                                </nav>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-4">
                                <div class="card mb-4">
                                    <form class="card-body text-center" action="${user}" method="post">
                                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
                                             class="rounded-circle img-fluid" style="width: 150px;">
                                        <h5 class="my-3">${customer.fullname}</h5>
                                        <div class="d-flex justify-content-center mb-2">
                                            <c:choose>
                                                <c:when test="${sessionScope.updateProfile == null}">
                                                    <button type="submit" class="btn btn-success fw-bold" name="updateProfile" value="update">Chỉnh sửa thông tin</button>
                                                </c:when>
                                                <c:when test="${sessionScope.updateProfile != null}">
                                                    <button type="submit" class="btn btn-danger fw-bold" name="finishUpdate" value="finish">Cập nhật thông tin</button>
                                                </c:when>
                                            </c:choose>                                            
                                        </div>
                                    </form>
                                </div>
                                <div class="card mb-4 mb-lg-0">
                                    <div class="card-body p-0">
                                        <ul class="list-group list-group-flush rounded-3">
                                            <a class="list-group-item d-flex justify-content-between align-items-center p-3"
                                            href="#">
                                                <i class="fa-solid fa-unlock-keyhole fa-2x text-danger"></i>
                                                <p class="mb-0 fw-bold">Cài đặt mật khẩu</p>
                                            </a>
                                            <a class="list-group-item d-flex justify-content-between align-items-center p-3"
                                            href="${invoicePage}">
                                                <i class="fa-solid fa-2x fa-file-invoice-dollar text-warning"></i>
                                                <p class="mb-0 fw-bold">Xem danh sách hóa đơn mua hàng</p>
                                            </a>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-8">
                                <div class="card mb-4">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <p class="mb-0">Full Name</p>
                                            </div>
                                            <div class="col-sm-9">
                                                <p class="text-muted mb-0">${customer.fullname}</p>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <p class="mb-0">Email</p>
                                            </div>
                                            <div class="col-sm-9">
                                                <p class="text-muted mb-0">${customer.email}</p>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <p class="mb-0">Điện thoại</p>
                                            </div>
                                            <div class="col-sm-9">
                                                <p class="text-muted mb-0">${customer.phoneNumber}</p>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <p class="mb-0">Address</p>
                                            </div>
                                            <div class="col-sm-9">
                                                <p class="text-muted mb-0">${customer.userAddress == "" ? "Chưa có" : customer.userAddress}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <div class="card mb-4 mb-md-0">
                                            <div class="card-body">
                                                <h4 class="mb-4"><span class="text-primary fw-bold font-italic me-1">Tổng tiền đã mua</span> : <span class="badge bg-secondary">${customer.totalPayAmount}.000 (VND)</span>
                                                </h4>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
