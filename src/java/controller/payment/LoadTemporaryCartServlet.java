/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.payment;

import controller.SupportEnum;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.customer.Customer;

/**
 *
 * @author This PC
 */
@WebServlet(name = "LoadTemporaryCartServlet", urlPatterns = {"/LoadTemporaryCartServlet", "/loadTempCart"})
public class LoadTemporaryCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Served at [" + getServletInfo() + "]");
        HttpSession session = request.getSession(true);

        Customer user = (Customer) session.getAttribute(SupportEnum.CUSTOMER.getName());
        if (user == null) 
        {
            processTemporaryCartCookie(request);
        } else {
            processUserTemporaryCart(session, user);
        }

        response.sendRedirect(webpage_tools.WebPageEnum.HOME.getURL());
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

    private void processTemporaryCartCookie(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals(SupportEnum.TEMPORARY_CART_COOKIE.getName())) {
                String cookieValue = cookie.getValue();
                Optional<TemporaryCart> temporaryCart = TemporaryCartManager.get(cookieValue);
                if (temporaryCart.isPresent()) {
                    System.out.println("Exist temporary Cart");
                    session.setAttribute(SupportEnum.TEMPORARY_CART.getName(), temporaryCart.get());
                }
            }
        }
    }

    private void processUserTemporaryCart(HttpSession session, Customer user) {
        String username = user.getUsername();
        Optional<TemporaryCart> temporaryCart = TemporaryCartManager.get(username);
        
        if (temporaryCart.isPresent()) 
        {
            System.out.println("Exist temporary Cart");
            session.setAttribute(SupportEnum.TEMPORARY_CART.getName(), temporaryCart.get());
        }
    }
}
