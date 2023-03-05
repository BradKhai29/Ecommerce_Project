<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
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
    </head>
    <body>
        <h1>LOGIN PAGE</h1>
        <div class="wrapper">
            <div class="div-login">
                <form action="login" accept-charset="UTF-8" method="post">
                    <table id="tbl-login">
                        <tr>
                            <td>User Name <span>*</span></td>
                            <td><input type="text" name="username" id="userName"
                                       placeholder="Tên đăng nhập" required ></td>
                        </tr>
                        <tr>
                            <td>Password <span>*</span></td>
                            <td><input type="password" name="password" id="password"
                                       placeholder="Mật khẩu" required/></td>
                        </tr>
                        <tr>
                            <td>\ <span>\</span></td>
                            <td>
                                <input type="checkbox" name="remember" id="remember"/>
                                <label for="remember">Ghi nhớ đăng nhập</label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button type="submit" name="login">Đăng nhập</button>
                                <a href = "${root}/registerPage" id = "link-register">Đăng kí tài khoản</a>
                            </td>
                        </tr>
                        ${requiredLogin}
                        ${loginError}
                    </table>
                </form>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
