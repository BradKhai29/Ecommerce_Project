<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if var="test" test="${sessionScope.admin == null}">
    <jsp:forward page="admin"></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>
        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
        </style>
    </head>
    <body>
        <header class="navbar navbar-expand navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
            <span class="navbar-brand col-md-3 col-lg-2 me-0 px-3">
                <i class="fa-solid fa-cubes"></i>BLOCKY
            </span>
            <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="bg-dark w-100 text-center text-light fw-bold">PRODUCTS ADMIN PAGE</div>
            <ul class="navbar-nav ms-auto ms-md-0 bg-danger align-items-center justify-content-center">
                <form class="nav-item dropdown px-2" action="admin" method="post">
                    <a class="nav-link dropdown-toggle text-dark" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Cài đặt tài khoản</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><button class="dropdown-item" type="submit" name="command" value="LOGOUT">Đăng xuất</button></li>
                    </ul>
                </form>
            </ul>
        </header>
        <main>
            <section class="py-5 text-center container fw-bold">
                <div class="row py-lg-5">
                    <div class="col-lg-6 col-md-8 mx-auto">
                        <h1 class="fw-light">Chi tiết sản phẩm</h1>
                        <p class="lead text-muted fw-bold">
                            Xem danh sách các sản phẩm có trong hệ thống
                        </p>
                        <p class="lead text-muted fw-bold">
                            Tạo mới sản phẩm tại đây
                        </p>
                        <p>
                            <a href="${root}/admin_page" class="btn btn-primary my-2">Về lại trang Admin</a>
                            <a href="#" class="btn btn-secondary my-2">Xem doanh thu sản phẩm <i class="fa-solid fa-money-check-dollar"></i></a>
                        </p>
                    </div>
                </div>
            </section>
            <section class="album py-5 mt-0 bg-light">
                <div class="container">
                    <div class="row mt-0 pt-0">
                        <div class="d-flex justify-content-end mb-4">
                            <!-- Button section -->
                            <a href="${root}/create_product" class="rounded-3 btn btn-success border-2 fw-bold">
                                <i class="fa-solid fa-square-plus"></i> Tạo mới sản phẩm
                            </a>
                        </div>
                    </div>
                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                        <c:forEach var="productEntry" items="${applicationScope.products}">
                            <c:set var="product" value="${productEntry.value}"></c:set>
                            <div class="col" id="product${product.productID}">
                                <div class="card shadow-sm">
                                    <form action="product" method="post">
                                        <input type="text" name="productID" value="${product.productID}" hidden>
                                        <button type="submit" class="card-img-top border-0 p-0 m-0" name="command" value="DETAIL_ADMIN">
                                            <img width="100%" height="320" class="card-img-top" src="${product.imgURL}" alt="">
                                        </button>
                                    </form>
                                    <ul class="card-body m-0 py-0">
                                        <div class="d-flex justify-content-between align-items-center pt-2">
                                            <div class="fw-bold">
                                                <span class="fw-bold text-primary">Mã sản phẩm</span> : ${product.productID}
                                            </div>
                                            <span class="fw-bold btn btn-outline-danger">${product.productStatus}</span>
                                        </div>
                                    </ul>
                                    <hr class="dropdown-divider mb-1">
                                    <div class="card-body mt-0">
                                        <h5 class="card-title">${product.productName}</h5>
                                        <p class="card-text text-muted fw-bold">
                                            <span class="fw-bold text-primary">Mô tả</span> : ${product.details}
                                        </p>
                                        <div class="d-flex justify-content-between align-items-center pt-4">
                                            <form class="btn-group" action="product" method="post">
                                                <input type="text" name="productID" value="${product.productID}" hidden>
                                                <button type="submit" class="rounded-start btn btn-outline-secondary" name="command" value="DETAIL_ADMIN">Xem chi tiết</button>
                                                <button type="submit" class="btn btn-outline-secondary" name="command" value="UPDATE">Chỉnh sửa</button>
                                            </form>
                                            <span class="badge fs-5 bg-warning text-muted">${product.price}.000 VND</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>
        </main>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
