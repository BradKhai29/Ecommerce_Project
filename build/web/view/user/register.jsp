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
        <div class="container">
            <div class="div-register">
                <form action="register" method="post"
                      onsubmit="return validateRegister()" name="frm-register">

                    <table id="tbl-register">
                        <tr>
                            <td>Full Name <span>*</span></td>
                            <td><input type="text" name="fullname" id="fullname" 
                                       placeholder="Họ và tên quý khách" value="${fullname == null ? "" : fullname}" required/></td>
                        </tr>
                        <tr>
                            <td>Phone number<span>*</span></td>
                            <td><input type="text" name="phoneNumber" 
                                       placeholder="Số điện thoại" value="${phoneNumber == null ? "" : phoneNumber}" required/></td>
                        </tr>
                        <tr>
                            <td>Địa chỉ<span>*</span></td>
                            <td><input type="text" name="userAddress" 
                                       placeholder="Địa chỉ (nếu có)" value="${userAddress == null ? "" : userAddress}"/></td>
                        </tr>
                        <tr>
                            <td>Email <span>*</span></td>
                            <td><input type="email" name="email" id="email" 
                                       placeholder="Email" value="${email == null ? "" : email}" required/></td>
                        </tr>
                        <tr>
                            <td>User Name <span>*</span></td>
                            <td><input type="text" name="username" id="username"
                                       placeholder="Tên đăng nhập (Username)" value="${username == null ? "" : username}" required/></td>
                        </tr>
                        <tr>
                            <td>Password <span>*</span></td>
                            <td><input type="password" name="password" id="password"
                                       placeholder="Mật khẩu (Password)" required/></td>
                        </tr>

                        <tr>
                            <td>Confirm Password <span>*</span></td>
                            <td><input type="password" name="confirmPassword"
                                       id="confirmPassword" placeholder="Nhập lại mật khẩu" required/></td>
                        </tr>
                        <tr>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <button type="submit" name="register">Đăng ký</button> 
                                <a href="${root}/loginPage" id="link-login">
                                    Login nếu đã có tài khoản
                                </a>
                            </td>
                        </tr>
                        ${existUsername}<br>
                        ${confirmPasswordError}<br>
                        ${phoneNumberError}<br>
                    </table>
                </form>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
