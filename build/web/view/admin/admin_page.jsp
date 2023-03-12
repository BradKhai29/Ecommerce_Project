<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if var="test" test="${sessionScope.admin == null}">
    <jsp:forward page="admin"></jsp:forward>
</c:if>
<!doctype html>
<html lang="en">
    <head>
        <title>ADMIN PAGE</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/dashboard/">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
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
        <link href="css/dashboard.css" rel="stylesheet">
    </head>
    <body>
        <header class="navbar navbar-expand navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
            <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="#">
              <i class="fa-solid fa-cubes"></i>BLOCKY
            </a>
            <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="bg-dark w-100 bg-opacity-50 text-center text-light fw-bold">ADMIN PAGE</div>
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
        <main class="container-fluid">
            <div class="row min-vh-100">
                <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse pb-5">
                    <div class="position-sticky py-4 h-100 pb-5">
                        <ul class="nav flex-column">
                            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                                <span>Thống kê</span>
                            </h6>
                            <li class="nav-item">
                                <a class="container nav-link active" aria-current="page" href="#">
                                    <div class="row">
                                        <div class="col-1"><i class="fa-solid fa-chart-line"></i></div>
                                        <div class="col-9">Dashboard</div>
                                    </div>
                                </a>
                            </li>
                            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                                <span>Quản lý sản phẩm</span>
                            </h6>
                            <li class="nav-item">
                                <a class="container nav-link active" aria-current="page" href="#">
                                    <div class="row">
                                        <div class="col-1"><i class="fa-solid fa-receipt"></i></div>
                                        <div class="col-9">Doanh thu sản phẩm</div>
                                    </div>
                                </a>
                                <a class="container nav-link active" aria-current="page" href="#">
                                    <div class="row">
                                        <div class="col-1 ">
                                            <i class="fa-solid fa-box-open"></i>
                                        </div>
                                        <div class="col-9">Chỉnh sửa thông tin</div>
                                    </div>
                                </a>
                            </li>
                            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                                <span>Quản lý khách hàng</span>
                            </h6>
                            <li class="nav-item">
                                <a class="container nav-link active" aria-current="page" href="#">
                                    <div class="row">
                                        <div class="col-1">
                                            <i class="fa-solid fa-house-user"></i>
                                        </div>
                                        <div class="col-9">Khách hàng tiềm năng</div>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
                <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                        <h1 class="h2">Dashboard</h1>
                    </div>
                    <div class="row fw-bold">
                        <div class="col-xl-3 col-md-6">
                            <div class="card bg-primary text-white mb-4">
                                <div class="card-body d-flex align-items-center justify-content-between">
                                    <span>Doanh thu</span>
                                    <div class="text-white fs-4">
                                        <span class="badge bg-dark">100.000.000 VND</span>
                                    </div>
                                </div>
                                <div class="card-footer d-flex align-items-center justify-content-between">
                                    <a class="small text-white stretched-link" href="#">Xem chi tiết</a>
                                    <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-md-6">
                            <div class="card bg-warning text-white mb-4">
                                <div class="card-body d-flex align-items-center justify-content-between">
                                    <span>Số lượng khách hàng</span>
                                    <div class="text-white fs-4">
                                        <span class="badge bg-dark">New</span>
                                    </div>
                                </div>
                                <div class="card-footer d-flex align-items-center justify-content-between">
                                    <a class="small text-white stretched-link" href="#">Xem chi tiết</a>
                                    <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-md-6">
                            <div class="card bg-success text-white mb-4">
                                <div class="card-body d-flex align-items-center justify-content-between">
                                    <span>Chi tiết các sản phẩm</span>
                                    <div class="text-white fs-4">
                                        <span class="badge bg-dark bg-opacity-50"><i class="fa-solid fa-circle-info"></i></span>
                                    </div>
                                </div>
                                <div class="card-footer d-flex align-items-center justify-content-between">
                                    <a class="small text-white stretched-link" href="${root}/products">Xem chi tiết</a>
                                    <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-md-6">
                            <div class="card bg-danger text-white mb-4">
                                <div class="card-body d-flex align-items-center justify-content-between">
                                    <span>Tạo mới sản phẩm</span>
                                    <div class="text-white fs-4">
                                        <span class="badge bg-dark bg-opacity-50"><i class="fa-solid fa-plus"></i></span>
                                    </div>
                                </div>
                                <div class="card-footer d-flex align-items-center justify-content-between">
                                    <a class="small text-white stretched-link" href="${root}/create_product">Đi tới trang tạo mới</a>
                                    <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </main>
        <footer class="text-center text-lg-start text-light bg-dark">
            <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2)">
                © Powered by: <a href="#" class="text-danger">Nguyen Duong Khai</a>
            </div>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous"></script>
        <script src="css/dashboard.js"></script>
    </body>
</html>
