/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import controller.support.CookieEnum;
import controller.support.CookieSupportServlet;
import controller.support.SupportEnum;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.customer.Customer;
import model.temporary_cart.TemporaryCartManager;

/**
 *
 * @author This PC
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet", "/logout"})
public class LogoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Served at [" + getServletName() + "]");
        
        removeTempCartIfEmpty(request);
        invalidateSession(request);
        removeRememberUserCookie(request, response);
        
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
    
    private void removeTempCartIfEmpty(HttpServletRequest request)
    {
        HttpSession session = request.getSession(true);
        
        //Get user in this session
        Customer user = (Customer)session.getAttribute(SupportEnum.CUSTOMER.getName());
        TemporaryCartManager.remove(user.getUsername(), true);
    }

    private void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        request.getSession(true);
    }

    private void removeRememberUserCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieSupportServlet.processCookie(request, response, CookieEnum.REMEMBER_USER_COOKIE, true);
    }

}
