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
                    <form class="container-xxl py-5" action="${user}" method="post">
                        <div class="row">
                            <div class="col">
                                <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
                                    <ol class="breadcrumb mb-0 fw-bold">
                                        <li class="breadcrumb-item"><a href="${user}">Home</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">User Profile</li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                        <c:if var="messageCheck" test="${message != null && success == null}">
                            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                                <strong>${message}</strong> 
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                        <c:if var="messageCheck" test="${message != null && success != null}">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                <strong>${message}</strong> 
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                        <div class="row">
                            <div class="col-lg-4">
                                <div class="card mb-4">
                                    <div class="card-body text-center" action="${user}" method="post">
                                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
                                             class="rounded-circle img-fluid" style="width: 150px;">
                                        <h5 class="my-3">${customer.fullname}</h5>
                                        <h6 class="my-3">Tên đăng nhập : ${customer.username}</h6>
                                        <div class="d-flex justify-content-center mb-2">
                                            <c:choose>
                                                <c:when test="${updateProfile == null && updatePassword == null}">
                                                    <button type="submit" class="btn btn-success fw-bold" name="doUpdate" value="UPDATE_PROFILE">Chỉnh sửa thông tin</button>
                                                </c:when>
                                                <c:when test="${updateProfile != null}">
                                                    <button type="submit" class="btn btn-warning fw-bold" name="doUpdate" value="CONFIRM_UPDATE">Cập nhật thông tin</button>
                                                </c:when>
                                            </c:choose>                                            
                                        </div>
                                    </div>
                                </div>
                                <c:if var="testPasswd" test="${updatePassword == null && updateProfile == null}">
                                    <div class="card mb-4 mb-lg-0">
                                        <div class="card-body p-0">
                                            <ul class="list-group list-group-flush rounded-3">
                                                <button class="list-group-item d-flex justify-content-between align-items-center p-3 btnlin"
                                                        type="submit" formaction="${user}" name="doUpdate" value="UPDATE_PASSWORD">
                                                    <i class="fa-solid fa-unlock-keyhole fa-2x text-danger"></i>
                                                    <p class="mb-0 fw-bold">Cài đặt mật khẩu</p>
                                                </button>
                                                <a class="list-group-item d-flex justify-content-between align-items-center p-3"
                                                href="${invoiceHistory}">
                                                    <i class="fa-solid fa-2x fa-file-invoice-dollar text-warning"></i>
                                                    <p class="mb-0 fw-bold">Xem danh sách hóa đơn mua hàng</p>
                                                </a>
                                            </ul>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                            <div class="col-lg-8">
                                <c:if var="testPasswd" test="${updatePassword == null}">
                                    <div class="card mb-4">
                                        <div class="card-body">
                                            <div class="row">
                                                <c:if var="test" test="${updateProfile == null}">
                                                    <div class="col-sm-3 fw-bold">
                                                        <p class="mb-0">Tên khách hàng</p>
                                                    </div>
                                                    <div class="col-sm-9">
                                                        <p class="text-muted mb-0">${customer.fullname}</p>
                                                    </div>
                                                </c:if>
                                                <c:if var="test" test="${updateProfile != null}">
                                                    <div class="input-group m-0">
                                                        <label class="input-group-text col-sm-3 fw-bold" for="fullname">Tên khách hàng</label>
                                                        <input name="fullname" type="text" class="form-control col-sm-9" id="fullname" value="${customer.fullname}" required>
                                                    </div>
                                                </c:if>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <c:if var="test" test="${updateProfile == null}">
                                                    <div class="col-sm-3 fw-bold">
                                                        <p class="mb-0">Email</p>
                                                    </div>
                                                    <div class="col-sm-9">
                                                        <p class="text-muted mb-0">${customer.email}</p>
                                                    </div>
                                                </c:if>
                                                <c:if var="test" test="${updateProfile != null}">
                                                    <div class="input-group m-0">
                                                        <label class="input-group-text col-sm-3 fw-bold" for="email">Email</label>
                                                        <input name="email" type="email" class="form-control col-sm-9" id="email" value="${customer.email}" required>
                                                    </div>
                                                </c:if>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <c:if var="test" test="${updateProfile == null}">
                                                    <div class="col-sm-3 fw-bold">
                                                        <p class="mb-0">Điện thoại</p>
                                                    </div>
                                                    <div class="col-sm-9">
                                                        <p class="text-muted mb-0">${customer.phoneNumber}</p>
                                                    </div>
                                                </c:if>
                                                <c:if var="test" test="${updateProfile != null}">
                                                    <div class="input-group m-0">
                                                        <label class="input-group-text col-sm-3 fw-bold" for="phoneNumber">Điện thoại</label>
                                                        <input name="phoneNumber" type="tel" class="form-control col-sm-9" id="phoneNumber" value="${customer.phoneNumber}" required>
                                                    </div>
                                                </c:if>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <c:if var="test" test="${updateProfile == null}">
                                                    <div class="col-sm-3 fw-bold">
                                                        <p class="mb-0">Địa chỉ</p>
                                                    </div>
                                                    <div class="col-sm-9">
                                                        <p class="text-muted mb-0">${customer.userAddress == "" ? "Chưa có" : customer.userAddress}</p>
                                                    </div>
                                                </c:if>
                                                <c:if var="test" test="${updateProfile != null}">
                                                    <div class="input-group m-0">
                                                        <label class="input-group-text col-sm-3 fw-bold" for="userAddress">Địa chỉ</label>
                                                        <input name="userAddress" type="text" class="form-control col-sm-9" id="userAddress" value="${customer.userAddress}">
                                                    </div>
                                                </c:if>
                                            </div>
                                            <c:if var="test" test="${updateProfile != null}">
                                                <br>
                                                <div class="row">
                                                    <div class="d-grid btn-group">
                                                        <a class="btn btn-danger col" href="${user}?cancel=true">Hủy bỏ</a>
                                                    </div>
                                                </div>
                                            </c:if>
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
                                </c:if>
                                <c:if var="testPasswd" test="${updatePassword != null}">
                                    <div class="card mb-4">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="container-sm">
                                                    <div class="alert alert-success" role="alert">
                                                        <h4 class="alert-heading">Thay đổi mật khẩu</h4>
                                                        <hr>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="input-group m-0">
                                                    <label class="input-group-text col-sm-3 fw-bold" for="fullname">Mật khẩu cũ</label>
                                                    <input name="oldPassword" type="password" class="form-control col-sm-9 rounded-end" id="fullname" required>
                                                    <button id="toggle-password" onclick="" type="button" class="btn btn-link" aria-label="Show password as plain text. Warning: this will display your password on the screen.">
                                                        <i class="fa-solid fa-eye"></i>
                                                    </button>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="input-group m-0">
                                                    <label class="input-group-text col-sm-3 fw-bold" for="fullname">Mật khẩu mới</label>
                                                    <input name="newPassword" type="password" class="form-control col-sm-9 rounded-end" id="fullname" required>
                                                    <button id="toggle-password" type="button" class="btn btn-link" aria-label="Show password as plain text. Warning: this will display your password on the screen.">
                                                        <i class="fa-solid fa-eye"></i>
                                                    </button>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="input-group m-0 ">
                                                    <label class="input-group-text col-sm-3 fw-bold" for="fullname">Nhập lại mật khẩu mới</label>
                                                    <input name="confirmNewPassword" type="password" class="form-control col-sm-9 rounded-end" id="fullname" spellcheck="false" autocorrect="off" autocapitalize="off" required>
                                                    <button id="toggle-password" type="button" class="btn btn-link" aria-label="Show password as plain text. Warning: this will display your password on the screen.">
                                                        <i class="fa-solid fa-eye"></i>
                                                    </button>
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row">
                                                <div class="d-flex">
                                                    <button class="btn btn-success col-sm-9 me-1" type="submit" name="doUpdate" value="CONFIRM_UPDATE">Xác nhận</button>
                                                    <a class="btn btn-danger col-sm-3" href="${user}?cancel=true">Hủy bỏ</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </form>
                </section>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
