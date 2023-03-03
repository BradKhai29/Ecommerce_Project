<%-- 
    Document   : template
    Created on : Jan 31, 2023, 2:35:36 PM
    Author     : This PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <h1>Have Bootstrap</h1>
        <div class="card" style="width: 18rem;" method="post">
            <input type="hidden" name="productID" value="${sessionScope.product.productID}">
            <div>
                <img src="${root}/${sessionScope.product.imgURL}" class="card-img-top" alt="image">
            </div>    
            <div class="card-body">
                <div>
                    <h5 class="card-title">Mô hình ${sessionScope.product.productName}</h5>
                </div>
                <p class="card-text">Mô tả ${sessionScope.product.details}</p>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">${sessionScope.product.price}</li>
                    <li class="list-group-item">Loại sản phẩm: ${sessionScope.product.typeName}</li>
                </ul>

                <div class="d-flex">
                    <button type="button" class="btn btn-primary btn-sm">Buy now</button>
                    <button type="button" class="btn btn-secondary btn-sm">Add To Cart</button>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
