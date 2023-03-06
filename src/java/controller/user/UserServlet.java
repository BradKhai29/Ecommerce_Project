/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import controller.SupportEnum;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import webpage_tools.PrintTools;

/**
 * This servlet will handle all functions related to user's profile
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet", "/user"})
public class UserServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(SupportEnum.UPDATE_PROFILE_CHECKPOINT.getName());
        
        response.sendRedirect(webpage_tools.WebPageEnum.HOME.getURL());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Served at [" + getServletName() + "]");
        request.setCharacterEncoding(PrintTools.getUTF8());
        
        HttpSession session = request.getSession();
        String updateProfile = request.getParameter(SupportEnum.UPDATE_PROFILE_CHECKPOINT.getName());
        
        boolean doUpdateProfile = updateProfile != null;
        
        if(doUpdateProfile) displayUpdate(request, response);
        else{
            
        }
        response.sendRedirect(webpage_tools.WebPageEnum.USER_DETAIL.getURL());
    }
    
    private void displayUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession();   
        session.setAttribute(SupportEnum.UPDATE_PROFILE_CHECKPOINT.getName(), "doUpdate");
    }
    
    private void finishUpdate()
    {
        
    }
}
