<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<c:if test="${applicationScope.products == null}">
    <jsp:forward page="root"></jsp:forward>
</c:if>
<!-- This is used for Login with Cookie -->
<c:if test="${sessionScope.CookieLoginCheckPoint == null}">
    <jsp:forward page="loginWithCookie"></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=devce-width, initial-scale=1.0">
        <link rel="stylesheet" href="${root}/asset/home/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>
    </head>
    <body>
        <header class="container-fluid">
            <div class="row">
                <!-- Navbar -->
                <nav class="navbar navbar-expand-lg navbar-scroll shadow-0 border-bottom border-dark">
                    <div class="container pt-1">
                        <div class="badge">
                            <a href="#" id="icon" class="btn"><i class="fa-solid fa-cubes"></i>BLOCKY</a>
                        </div>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ms-auto fw-bold">
                                <li class="nav-item fw-bold">
                                    <a class="nav-link active" aria-current="page" href="#">Catalog</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="#">About Us</a>
                                </li>
                                <li class="nav-item">
                                    <a class="btn btn-warning position-relative p-2 fw-bold" href="${cart}">
                                        Giỏ hàng
                                        <i class="fa-brands fa-shopify"></i>
                                        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-dark">
                                            ${temporaryCart == null ? "0" : temporaryCart.size}
                                        </span>
                                    </a>
                                </li>
                                <c:if var="test" test="${customer == null}">
                                    <a class="btn btn-dark ms-3" href="${login}"><i class="fa-solid fa-circle-user icon"></i>Đăng nhập</a>
                                </c:if>
                                <c:if var="test2" test="${customer != null}">
                                    <div class="dropdown">
                                        <button class="btn btn-dark dropdown-toggle ms-3" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                            <i class="fa-solid fa-circle-user icon"></i>${customer.username} 
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                            <li><a class="dropdown-item" href="${user}?cancel=true">Cài đặt tài khoản</a></li>
                                            <li><hr class="dropdown-divider"></li>
                                            <li><a class="dropdown-item" href="${invoiceHistory}">Xem danh sách hóa đơn</a></li>
                                            <li><hr class="dropdown-divider"></li>
                                            <li><a class="dropdown-item" href="logout">Đăng xuất</a></li>
                                        </ul>
                                    </div>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </header>
        <main class="container-fluid">
            <div class="row">
                <c:forEach var="product" items="${applicationScope.products}">
                    <form class="card" style="width: 18rem;" method="post">
                        <input type="hidden" name="productID" value="${product.key}">
                        <button type="submit" formaction="${applicationScope.productServlet}" name="command" value="DETAIL">
                            <img src="${root}/${product.value.imgURL}" class="card-img-top" alt="image">
                        </button>    
                        <div class="card-body">
                            <button type="submit" formaction="${applicationScope.productServlet}" name="command" value="DETAIL">
                                <h5 class="card-title">Mô hình ${product.value.productName}</h5>
                            </button>
                            <p class="card-text">Mô tả ${product.value.details}</p>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">${product.value.price}</li>
                                <li class="list-group-item">Loại sản phẩm: ${product.value.typeName}</li>
                            </ul>
                            <div class="d-flex">
                                <button type="submit" name="buyNow" formaction="productCart" class="btn btn-primary btn-sm">Mua ngay</button>
                                <button type="submit" name="addToCart" formaction="productCart" class="btn btn-secondary btn-sm">Thêm vào giỏ hàng</button>
                            </div>
                        </div>
                    </form>
                </c:forEach>
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
