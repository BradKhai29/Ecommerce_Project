/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webpage_tools.*;

/**
 *
 * @author This PC
 */
@WebServlet(name = "RootController", urlPatterns = {"/RootController", "/root"})
public class RootController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Served at [" + getServletInfo() + "]");
        initURLs(request);
        response.sendRedirect(ServletEnum.PRODUCT_LOAD.getURL());
    }
    
    private void initURLs(HttpServletRequest request)
    {
        ServletContext application = request.getServletContext();
        application.setAttribute("root", application.getContextPath());
        
        //Init the URLs
        String loginPage = WebPageEnum.LOGIN_PAGE.getURL();
        String registerPage = WebPageEnum.REGISTER_PAGE.getURL();
        
        //Set URLS for applicationScope
        application.setAttribute("login", loginPage);
        application.setAttribute("register", registerPage);
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

}
