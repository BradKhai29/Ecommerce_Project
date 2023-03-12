<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if var="test" test="${sessionScope.customer != null}">
    <c:redirect context="${root}" url="/"></c:redirect>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <title>LOGIN Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>
        <link rel="stylesheet" href="${root}/asset/home/style.css">
    </head>
    <body>
        <header class="container-fluid">
            <div class="row">
                <!-- Navbar -->
                <nav class="navbar navbar-expand-lg navbar-scroll shadow-0 border-bottom border-dark">
                    <div class="container pt-1">
                        <div class="badge">
                            <a href="${root}" id="icon" class="btn"><i class="fa-solid fa-cubes"></i>BLOCKY</a>
                        </div>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ms-auto fw-bold">
                                <li class="nav-item">
                                    <a class="btn btn-warning position-relative p-2 fw-bold" href="${cart}">
                                        Giỏ hàng
                                        <i class="fa-brands fa-shopify"></i>
                                        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-dark">
                                            ${temporaryCart == null ? "0" : temporaryCart.size}
                                        </span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </header>
        <main class="container-fluid">
            <div class="row">
                <section class="container-fluid rounded-3 p-5">
                    <div class="container-md h-custom">
                        <div class="row">
                            <nav aria-label="breadcrumb" class="bg-dark text-light rounded-3 p-3 px-5 mb-4">
                                <ol class="breadcrumb mb-0 fw-bold text-light">
                                    <li class="breadcrumb-item"><a href="${user}" class="text-light">Home</a></li>
                                    <li class="breadcrumb-item active text-light" aria-current="page">Login</li>
                                </ol>
                            </nav>
                        </div>
                        <div class="row bg-light d-flex justify-content-center align-items-center border border-3 rounded-3">
                            <div class="col-md-9 col-lg-6 col-xl-5">
                                <img src="https://img.wattpad.com/bf2b6c2358bc5a569a2ff4b007f84ca6ab326d4f/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f776174747061642d6d656469612d736572766963652f53746f7279496d6167652f3535392d4a7773467979464f41413d3d2d3830343136323030342e313564363137353765353765613136363334333032373433383239312e6a7067?s=fit&w=720&h=720"
                                     class="img-fluid" alt="Sample image">
                            </div>
                            <div class="col-md-8 col-lg-6 col-xl-4 offset-1">
                                <form action="login" accept-charset="UTF-8" method="post">
                                    <div class="divider d-flex align-items-center my-4">
                                        <h4 class="text-center fw-bold mx-3 mb-0"><span class="badge bg-primary bg-opacity-75">ĐĂNG NHẬP</span></h4>
                                    </div>

                                    <!-- Email input -->
                                    <div class="form-outline mb-4">
                                        <input type="text" name="username" id="form3Example3" class="form-control form-control-lg"
                                               placeholder="Tên đăng nhập" />
                                    </div>

                                    <!-- Password input -->
                                    <div class="form-outline mb-3">
                                        <input type="password" name="password" class="form-control form-control-lg"
                                               placeholder="Mật khẩu" required/>
                                    </div>

                                    <div class="d-flex justify-content-between align-items-center">
                                        <!-- Checkbox -->
                                        <div class="form-check mb-0">
                                            <input class="form-check-input me-2" type="checkbox" name="remember" id="remember" />
                                            <label class="form-check-label" for="remember">
                                                Ghi nhớ đăng nhập
                                            </label>
                                        </div>
                                    </div>

                                    <div class="text-center text-lg-start mt-4 pt-2">
                                        <div class="d-grid gap-2">
                                            <button type="submit" name="login" type="button" class="btn btn-primary btn-lg"
                                                    style="padding-left: 2.5rem; padding-right: 2.5rem;">Đăng nhập</button>
                                        </div>
                                        <p class="small fw-bold mt-2 pt-1 mb-0">Chưa có tài khoản? <a href="${register}" class="link-danger"> Đăng ký ngay</a></p>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </main>
        <footer class="text-center text-lg-start text-light bg-dark">
            <section class="">
                <div class="container text-center text-md-start mt-5">
                    <!-- Grid row -->
                    <div class="row mt-3 pt-3">
                        <!-- Grid column -->
                        <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                            <!-- Content -->
                            <h6 class="text-uppercase fw-bold">Company name</h6>
                            <hr class="mb-4 mt-0 d-inline-block mx-auto myslash"/>
                            <p>
                                Here you can use rows and columns to organize your footer
                                content. Lorem ipsum dolor sit amet, consectetur adipisicing
                                elit.
                            </p>
                        </div>
                        <!-- Grid column -->

                        <!-- Grid column -->
                        <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">
                            <!-- Links -->
                            <h6 class="text-uppercase fw-bold">Products</h6>
                            <hr class="mb-4 mt-0 d-inline-block mx-auto myslash"/>
                            <p>
                                <a href="#!" class="text-dark">MDBootstrap</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">MDWordPress</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">BrandFlow</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">Bootstrap Angular</a>
                            </p>
                        </div>
                        <!-- Grid column -->

                        <!-- Grid column -->
                        <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
                            <!-- Links -->
                            <h6 class="text-uppercase fw-bold">Useful links</h6>
                            <hr class="mb-4 mt-0 d-inline-block mx-auto myslash"/>
                            <p>
                                <a href="#!" class="text-dark">Your Account</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">Become an Affiliate</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">Shipping Rates</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">Help</a>
                            </p>
                        </div>
                        <!-- Grid column -->

                        <!-- Grid column -->
                        <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
                            <!-- Links -->
                            <h6 class="text-uppercase fw-bold">Contact</h6>
                            <hr class="mb-4 mt-0 d-inline-block mx-auto myslash"/>

                            <p><i class="fas fa-home mr-3"></i> New York, NY 10012, US</p>
                            <p><i class="fas fa-envelope mr-3"></i> info@example.com</p>
                            <p><i class="fas fa-phone mr-3"></i></p>
                            <p><i class="fas fa-print mr-3"></i> + 01 234 567 89</p>
                        </div>
                        <!-- Grid column -->
                    </div>
                    <!-- Grid row -->
                </div>
            </section>
            <!-- Section: Links  -->

            <!-- Copyright -->
            <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2)">
                © Visit Admin Page: <a href="${adminPage}" class="text-danger">Admin Page</a>
            </div>
            <!-- Copyright -->
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
