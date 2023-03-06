<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<c:if test="${applicationScope.products == null}">
    <jsp:forward page="root"></jsp:forward>
</c:if>
<c:if test="${sessionScope.CookieLoginCheckPoint == null}">
    <jsp:forward page="loginWithCookie"></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=devce-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container">
                        <a class="navbar-brand" href="#">Navbar</a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="#">Catalog</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="#">About Us</a>
                                </li>
                            </ul>
                            <div class="d-flex flex-row">
                                <h2>
                                    <c:if var="test" test="${customer == null}">
                                        <a href="${login}" class="btn">
                                            <h4>Login
                                                <span class="badge bg-light text-dark">
                                                    <i class="fa-solid fa-circle-user"></i>
                                                </span>
                                            </h4>
                                        </a>
                                    </c:if>
                                    <c:if var="test2" test="${customer != null}">
                                        <div class="dropdown">
                                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                                ${customer.username} <i class="fa-solid fa-circle-user"></i>
                                            </button>
                                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                                <li><a class="dropdown-item" href="${userDetail}">Cài đặt tài khoản</a></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><a class="dropdown-item" href="invoiceHistory">Xem danh sách hóa đơn</a></li>
                                                <li><hr class="dropdown-divider"></li>
                                                <li><a class="dropdown-item" href="logout">Đăng xuất</a></li>
                                            </ul>
                                        </div>
                                    </c:if>
                                    <a href="${cart}">
                                        <span class="badge bg-secondary">Cart
                                            <i class="fa-solid fa-cart-shopping"></i>
                                            <span class="badge bg-success">${temporaryCart == null ? "0" : temporaryCart.size}</span>
                                        </span>
                                    </a>
                                </h2>
                            </div>
                        </div>
                    </div>
                </nav>
                <c:forEach var="product" items="${applicationScope.products}">
                    <form class="card" style="width: 18rem;" method="post">
                        <input type="hidden" name="productID" value="${product.key}">
                        <button type="submit" formaction="${root}/productDetail">
                            <img src="${root}/${product.value.imgURL}" class="card-img-top" alt="image">
                        </button>    
                        <div class="card-body">
                            <button type="submit" formaction="${root}/productDetail">
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
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
