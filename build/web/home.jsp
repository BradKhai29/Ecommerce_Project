<%-- 
    Document   : template
    Created on : Jan 31, 2023, 2:35:36 PM
    Author     : This PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
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
                            <div class="d-flex">
                                <h2>
                                    <a href="#">
                                        <span class="badge bg-secondary">Cart
                                            <i class="fa-solid fa-cart-shopping"></i>
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
                                <button type="button" class="btn btn-primary btn-sm">Buy now</button>
                                <button type="button" class="btn btn-secondary btn-sm">Add To Cart</button>
                            </div>
                        </div>
                    </form>
                </c:forEach>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
