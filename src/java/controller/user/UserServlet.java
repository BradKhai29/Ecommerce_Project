/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import controller.support.SupportEnum;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.customer.Customer;
import model.customer.CustomerDAO;
import webpage_tools.FolderEnum;
import webpage_tools.PrintTools;

/**
 * This servlet will handle all functions related to user's profile
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet", "/user"})
public class UserServlet extends HttpServlet {
    private static final CustomerDAO customerDAO;
    
    static {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        removeUnusedAttribute(session);
        
        String cancel = request.getParameter("cancel");
        if(cancel == null) response.sendRedirect(webpage_tools.WebPageEnum.HOME.getURL());
        else response.sendRedirect(webpage_tools.WebPageEnum.USER_DETAIL.getURL());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Served at [" + getServletName() + "]");
        request.setCharacterEncoding(PrintTools.getUTF8());
        request.getSession().removeAttribute("success");
        request.getSession().removeAttribute("message");
        
        String command = request.getParameter("doUpdate");
        UpdateCommandEnum updateCommandEnum = UpdateCommandEnum.getCommandEnum(command);

        switch (updateCommandEnum) 
        {
            case UPDATE_PROFILE:
                updateProfile(request);
                break;
            case UPDATE_PASSWORD:
                updatePassword(request);
                break;
            case CONFIRM_UPDATE:
                confirmUpdate(request);
                break;
            default:
                
        }
        response.sendRedirect(webpage_tools.WebPageEnum.USER_DETAIL.getURL());
    }

    private void updateProfile(HttpServletRequest request)
    {
        String updateProfile = "";
        request.getSession().setAttribute("updateProfile", updateProfile);
    }

    private void confirmUpdate(HttpServletRequest request) 
    {
        HttpSession session = request.getSession();
        
        Customer user = (Customer)session.getAttribute(SupportEnum.CUSTOMER.getName());
        boolean isUpdateProfile = session.getAttribute("updateProfile") != null;
        boolean isUpdatePassword = session.getAttribute("updatePassword") != null;
        
        //Init message
        String message = "MẬT KHẨU NHẬP LẠI KHÔNG ĐÚNG, VUI LÒNG NHẬP LẠI";
        if(isUpdateProfile)
        {
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("userAddress");
            
            customerDAO.updateProfile(user, fullname, email, phoneNumber, address);
            message = "CẬP NHẬT THÔNG TIN THÀNH CÔNG";
            request.getSession().removeAttribute("updateProfile");
            request.getSession().setAttribute("message", message);
            session.setAttribute("success", "");            
        }
        
        if (isUpdatePassword) 
        {
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmNewPassword = request.getParameter("confirmNewPassword");
            
            if(oldPassword.equals(user.getPasswd())) {
                boolean isSimilar = confirmNewPassword.equals(newPassword);
                
                if (isSimilar) {
                    message = "THAY ĐỔI MẬT KHẨU THÀNH CÔNG";
                    session.setAttribute("success", "");
                    customerDAO.updatePassword(user, newPassword);
                    request.getSession().removeAttribute("updatePassword");
                }
            }
            else{
                message = "MẬT KHẨU CŨ KHÔNG ĐÚNG, VUI LÒNG NHẬP LẠI";
            }
            
            session.setAttribute("message", message);
        }
    }
    
    private void updatePassword(HttpServletRequest request)
    {
        String updatePassword = "";
        request.getSession().setAttribute("updatePassword", updatePassword);
    }
    
    private void removeUnusedAttribute(HttpSession session){
        session.removeAttribute("message");
        session.removeAttribute("success");
        session.removeAttribute(SupportEnum.UPDATE_PROFILE_CHECKPOINT.getName());
        session.removeAttribute(SupportEnum.UPDATE_PASSWORD_CHECKPOINT.getName());
    }
}
