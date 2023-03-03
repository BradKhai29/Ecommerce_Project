<%-- 
    Document   : template
    Created on : Jan 31, 2023, 2:35:36 PM
    Author     : This PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <div class="label-login">
                <p>LOGIN</p>
            </div>
            <div class="div-login">
                <form action="<%=request.getContextPath()%>/login" method="post" name="frm-login" onsubmit = "return validateLogin()">
                    <table id="tbl-login">

                        <tr>
                            <td>User Name <span>*</span></td>
                            <td><input type="text" name="userName" id="userName"
                                       placeholder="Enter user name" required ></td>
                        </tr>
                        <tr>
                            <td>Password <span>*</span></td>
                            <td><input type="password" name="password" id="password"
                                       placeholder="Enter password" required/></td>
                        </tr>
                        <tr>
                            <td>\ <span>\</span></td>
                            <td>
                                <input type="checkbox" name="remember" id="remember"/>
                                <label for="remember">Remember me</label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                        <c:if var="result" test="${errorMessage != null}">
                            <span class="error">${errorMessage}</span>
                            <%
                                session.removeAttribute("errorMessage");
                            %>
                        </c:if>
                        </td>
                        <td>
                            <button type="submit"
                                    name="login">Login</button>
                            <a href = "${root}/" id = "link-register">Click here to Register</a>
                        </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
