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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <h1>home page</h1>
        <div class="container">
            <div class="row">
                Display products map here:
                <c:forEach var="product" items="${applicationScope.products}">
                    <div class="card" style="width: 18rem;">
                        <img src="${product.value.imgURL}" class="card-img-top" alt="image">
                        <div class="card-body">
                            <h5 class="card-title">${product.value.productName}</h5>
                            <p class="card-text">${product.value.details}</p>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">${product.value.price}</li>
                            </ul>
                            <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
