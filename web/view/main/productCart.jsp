<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vn">
    <head>
        <title>PRODUCT CART Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${root}/asset/home/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
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
                                <li class="nav-item fw-bold">
                                    <a class="nav-link active" aria-current="page" href="#">Catalog</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="#">About Us</a>
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
        <div class="container-fluid">
            <div class="row">
                <div class="container">
                    <div class="row">
                        <h1>PRODUCT CART PAGE</h1>
                        <c:if var="test" test="${sessionScope.temporaryCart != null && sessionScope.temporaryCart.size > 0}">
                            <table class="table table-success">
                                <thead>
                                    <tr>
                                        <th scope="col" colspan="5"></th>
                                        <th scope="col" colspan="2">
                                            <form action="changeQuantity" method="post">
                                                <button type="submit" name="clear" class="btn btn-danger btn-sm">
                                                    Xoá giỏ hàng <i class="fa-solid fa-circle-check"></i>
                                                </button>
                                            </form>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th scope="col">Mã sản phẩm</th>
                                        <th scope="col">Hình ảnh</th>
                                        <th scope="col">Tên sản phẩm</th>
                                        <th scope="col">Đơn giá</th>
                                        <th scope="col">Số lượng</th>
                                        <th scope="col">Tổng tiền</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="product" items="${sessionScope.temporaryCart.productCart}">
                                        <tr>
                                    <form action="changeQuantity" method="post">
                                        <input type="hidden" name="productID" value="${product.key}">
                                        <th scope="row">${product.key}</th>
                                        <td><img class="img-fluid img-thumbnail w-25 h-25"
                                                 src="${root}/${product.value.imgURL}" alt="productImg"></td>
                                        <td>${product.value.productName}</td>
                                        <td>${product.value.price}</td>
                                        <td>
                                            <button type="submit" name="minus" class="btn btn-primary btn-sm">
                                                <i class="fa-solid fa-circle-minus"></i>
                                            </button>
                                            <input type="number" inputmode="numeric" name="quantity" value="${product.value.paymentQuantity}">
                                            <button type="submit" name="plus" class="btn btn-primary btn-sm">
                                                <i class="fa-solid fa-circle-plus"></i>
                                            </button>
                                        </td>
                                        <td>${product.value.totalPrice}.000</td>
                                        <td>
                                            <button type="submit" name="delete" class="btn btn-danger btn-sm">
                                                <i class="fa-solid fa-trash"></i>
                                            </button>
                                        </td>
                                    </form>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <th scope="row" colspan="5">Tổng tiền hóa đơn</th>
                                    <td colspan="2">${sessionScope.temporaryCart.totalPrice}.000</td>
                                </tr>
                                </tbody>
                            </table>
                            <a href="${root}/payment" class="btn btn-danger">Thanh toán</a>
                            <a href="${root}" class="btn btn-primary">Trang Chủ</a>
                        </c:if>
                    </div>
                </div>
                <div class="container">
                    <c:if var="test2" test="${sessionScope.temporaryCart == null || sessionScope.temporaryCart.size == 0}">
                        <div class="card">
                            <div class="card-header">
                                GIỎ HÀNG ĐANG TRỐNG
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">Quay lại trang chủ để tiếp tục mua hàng nha</h5>
                                <p class="card-text">...</p>
                                <a href="${root}" class="btn btn-primary">Trang Chủ</a>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
    </body>
</html>