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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author This PC
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet", "/logout"})
public class LogoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

    private void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        request.getSession(true);
    }

    private void removeRememberUserCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieSupportServlet.removeCookie(request, response, CookieEnum.REMEMBER_USER_COOKIE);
    }

}
