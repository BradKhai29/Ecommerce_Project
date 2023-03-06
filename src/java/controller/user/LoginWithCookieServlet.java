/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import controller.CookieEnum;
import controller.CookieSupportServlet;
import controller.SupportEnum;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.customer.Customer;
import webpage_tools.ServletEnum;
import webpage_tools.WebPageEnum;

/**
 *
 * @author This PC
 */
@WebServlet(name = "LoginWithCookie", urlPatterns = {"/LoginWithCookie", "/loginWithCookie"})
public class LoginWithCookieServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Served at [" + getServletInfo() + "]");
        HttpSession session = request.getSession(true);
        session.setAttribute(SupportEnum.COOKIE_LOGIN_CHECKPOINT.getName(), "checkpoint");
        
        //Get the Cookie
        Optional<Customer> rememeberUser = processRemberUserCookie(request, response);

        if (rememeberUser.isPresent()) {
            session.setAttribute(SupportEnum.CUSTOMER.getName(), rememeberUser.get());
        } 
        
        //After load the User, redirect to load the temporary Cart
        response.sendRedirect(ServletEnum.TEMP_CART_LOAD.getURL());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return getServletName();
    }

    private Optional<Customer> processRemberUserCookie(HttpServletRequest request, HttpServletResponse response) {
        //Init empty Optional
        System.out.println("processing RemberUserCookie ...");
        Optional<Customer> rememberUser = Optional.empty();

        //Get remember user cookie enum
        CookieEnum cookieEnum = CookieEnum.REMEMBER_USER_COOKIE;
        //Get cookieValue to reload again the remember user
        String cookieValue = CookieSupportServlet.processCookie(request, response, cookieEnum, false);
        
        //Get remeber user from RememberUserManager
        rememberUser = RememberUserManager.get(cookieValue);
        System.out.println("Finishing processed RemberUserCookie ...");
        return rememberUser;
    }
}
