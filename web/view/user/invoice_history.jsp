<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if var="test" test="${sessionScope.customer == null}">
    <jsp:forward page="${root}"></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <title>Invoices Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <h1>Invoices page</h1>
        <div class="container">
            <div class="row">
                <c:forEach var="invoiceEntry" items="${sessionScope.invoices}">
                    <table class="table table-success">
                        <thead>
                            <tr>
                                <th scope="col" colspan="6">Mã hóa đơn : ${invoiceEntry.key}</th>
                            </tr>
                            <tr>
                                <th scope="col">Mã sản phẩm</th>
                                <th scope="col">Hình ảnh</th>
                                <th scope="col">Tên sản phẩm</th>
                                <th scope="col">Đơn giá</th>
                                <th scope="col">Số lượng</th>
                                <th scope="col">Tổng tiền</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="productEntry" items="${invoiceEntry.value.temporaryCart.productCart}">
                                <tr>   
                                    <th scope="row">${productEntry.key}</th>
                                    <td>
                                        <img class="img-fluid img-thumbnail w-25 h-25"src="${root}/${productEntry.value.imgURL}" alt="productImg">
                                    </td>
                                    <td>${productEntry.value.productName}</td>
                                    <td>${productEntry.value.price}</td>
                                    <td>${productEntry.value.paymentQuantity}</td>
                                    <td>${productEntry.value.totalPrice}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>     
                </c:forEach>
            </div>
        </div>

        <a href="${root}" class="btn btn-primary">Trang Chủ</a>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
