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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>
    </head>
    <body>
        <header class="navbar navbar-expand navbar-dark sticky-top bg-dark flex-md-nowrap p-0 mb-5 shadow">
            <span class="navbar-brand col-md-3 col-lg-2 me-0 px-3">
                <i class="fa-solid fa-cubes"></i>BLOCKY
            </span>
            <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="bg-dark w-100 text-center text-light fw-bold">PRODUCT UPDATE INFORMATION PAGE</div>
            <ul class="navbar-nav ms-auto ms-md-0 bg-danger align-items-center justify-content-center">
                <form class="nav-item dropdown px-2" action="admin" method="post">
                    <a class="nav-link dropdown-toggle text-dark" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fas fa-user fa-fw"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Cài đặt tài khoản</a></li>
                        <li><hr class="dropdown-divider"/></li>
                        <li><button class="dropdown-item" type="submit" name="command" value="LOGOUT">Đăng xuất</button></li>
                    </ul>
                </form>
            </ul>
        </header>
        <main class="container">
            <div class="row">
                <nav aria-label="breadcrumb" class="bg-dark text-light rounded-3 p-3 px-5 mb-4">
                    <ol class="breadcrumb mb-0 fw-bold text-light">
                        <li class="breadcrumb-item">
                            <a href="${root}/admin_page" class="text-light">Admin</a>
                        </li>
                        <li class="breadcrumb-item active text-light" aria-current="page">
                            <a href="${root}/products" class="text-light">Products</a>
                        </li>
                        <li class="breadcrumb-item active text-light" aria-current="page">Update Product Info</li>
                    </ol>
                </nav>
            </div>
            <div class="row">
                <div class="card">
                    <div class="card-body">
                        <form class="row" action="product" method="post">
                            <div class="col-lg-5 col-md-5 col-sm-6 rounded-3">
                                <div class="white-box text-center rounded-3">
                                    <img width="100%" class="card-img-top rounded-3" height="440px" src="${root}/${product.imgURL}" alt="product image">
                                </div>
                            </div>
                            <section class="col-lg-7 col-md-7 col-sm-6 mt-0">
                                <input type="number" name="productID" value="${product.productID}" hidden>
                                <div class="d-flex ${product.productStatus == 4 ? 'justify-content-start' : 'justify-content-between'} mb-2">
                                    <!-- Button section -->
                                    <a href="${root}/products" class="rounded-3 btn btn-primary border-2 fw-bold">
                                        <i class="fa-solid fa-backward"></i> Về lại trang trước
                                    </a>
                                    <button type="button" class="rounded-3 btn btn-danger border-2 fw-bold" data-bs-toggle="modal" data-bs-target="#staticBackdrop" ${product.productStatus == 4 ? 'hidden' : ''}>Xóa sản phẩm</button>
                                    <!-- Modal section -->
                                    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="staticBackdropLabel">Xác nhận xóa sản phẩm</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    Lưu ý: Thao tác này sẽ xóa sản phẩm khỏi danh sách của Shop
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary fw-bold" data-bs-dismiss="modal">Hủy</button>
                                                    <button type="submit" class="btn btn-primary fw-bold" name="command" value="DELETE">Xác nhận xóa</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <h5 class="input-group mb-3">
                                    <span class="input-group-text col-3" id="inputGroup-sizing-default">Tên sản phẩm</span>
                                    <input type="text" name="productName" class="form-control fw-bold col-8" value="${product.productName}" ${sessionScope.UPDATE == null ? 'disabled' : ''} required>
                                </h5>
                                <h5 class="input-group mb-3">
                                    <label class="input-group-text col-3" for="typeName">Loại sản phẩm</label>
                                    <select name="productType" id="typeName" class="form-select fw-bold col-8" ${sessionScope.UPDATE == null ? 'disabled' : ''} required>
                                        <option value="1" ${product.typeID == 1 ? 'selected' : ''}>Kiếm Katana</option>
                                        <option value="2" ${product.typeID == 2 ? 'selected' : ''}>One Piece</option>
                                        <option value="3" ${product.typeID == 3 ? 'selected' : ''}>12 Cung hoàng đạo</option>
                                    </select>
                                </h5>
                                <h5 class="input-group mb-3">
                                    <span class="input-group-text col-3" id="inputGroup-sizing-default">Thương hiệu</span>
                                    <input type="text" name="productBrand" class="form-control fw-bold col-8" value="Connection Block - Linkgo" ${sessionScope.UPDATE == null ? 'disabled' : 'disabled'} required>
                                </h5>
                                <h5 class="input-group mb-3">
                                    <span class="input-group-text col-3" id="inputGroup-sizing-default">Mô tả sản phẩm</span>
                                    <textarea name="details" class="form-control fw-bold col-8" ${sessionScope.UPDATE == null ? 'disabled' : '' } required>${product.details}</textarea>
                                </h5>
                                <h5 class="input-group mb-3">
                                    <span class="input-group-text col-3" id="inputGroup-sizing-default">Giá bán </span>
                                    <input type="number" name="price" class="form-control fw-bold col-8" value="${product.price}" ${sessionScope.UPDATE == null ? 'disabled' : ''} required>
                                </h5>
                                <h5 class="input-group mb-3">
                                    <label class="input-group-text col-3" for="productStatus">Tình trạng</label>
                                    <select name="productStatus" id="productStatus" class="form-select fw-bold col-8" ${sessionScope.UPDATE == null ? 'disabled' : ''} required>
                                        <option value="1" ${product.productStatus == 1 ? 'selected' : ''}>Còn hàng</option>
                                        <option value="2" ${product.productStatus == 2 ? 'selected' : ''}>Hết hàng</option>
                                        <option value="3" ${product.productStatus == 3 ? 'selected' : ''}>Hàng sắp về</option>
                                        <option value="4" class="text-danger fw-bold rounded-bottom rounded-3" ${product.productStatus == 4 ? 'selected' : ''}>Xóa khỏi shop</option>
                                    </select>
                                </h5>
                                <div class="btn-group d-flex gap-2">
                                    <c:if var="test" test="${UPDATE == null}">
                                        <button type="submit" class="rounded-start btn btn-outline-danger border-2 fw-bold" name="command" value="UPDATE">Chỉnh sửa</button>
                                    </c:if>
                                    <c:if var="test" test="${UPDATE != null}">
                                        <button type="submit" class="col-8 rounded-3 btn btn-warning border-2 fw-bold" name="command" value="CONFIRM_UPDATE">Cập nhật thông tin</button>
                                        <a class="col-3 rounded-3 btn btn-danger border-2 fw-bold" href="${root}/product?command=DETAIL_ADMIN&productID=${product.productID}">Hủy bỏ</a>
                                    </c:if>
                                </div>
                            </section>
                            <div class="col-lg-12 col-md-12 col-sm-12">
                                <h3 class="box-title mt-4">General Info</h3>
                                <div class="table-responsive">
                                    <table class="table table-striped table-product table-hover" aria-describedby="This table">
                                        <thead>
                                        <th>Thông tin</th>
                                        <th>Chi tiết</th>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>Số lượng đã bán</td>
                                                <td>100</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </main>
        <footer></footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
