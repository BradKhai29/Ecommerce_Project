/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.user.RememberUserManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author This PC
 */
@WebServlet(name = "CookieSupportServlet", urlPatterns = {"/CookieSupportServlet", "/cookieSupport"})
public class CookieSupportServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    
    public static void addCookieWithName(HttpServletRequest request, HttpServletResponse response, CookieEnum cookieEnum, String cookieValue)
    {
        
    }
    
    /**
     * This method will remove Cookie with given CookieEnum and return the value belong to the removed cookie
     * @param request
     * @param response
     * @param cookieEnum
     * @return CookieValue belong to removed Cookie
     */
    public static String removeCookie(HttpServletRequest request, HttpServletResponse response, CookieEnum cookieEnum)
    {
        String cookieValue = "";
        Cookie[] cookies = request.getCookies();

        for (int i = 0; i < cookies.length; i++) {
            Cookie existCookie = cookies[i];
            
            //If exist cookie with name = this cookieEnum.name, then Remove it
            if (existCookie.getName().equals(cookieEnum.getName())) 
            {
                cookieValue = existCookie.getValue();
                existCookie.setMaxAge(0);
                response.addCookie(existCookie);
            }
        }
        
        return cookieValue;
    }
}
