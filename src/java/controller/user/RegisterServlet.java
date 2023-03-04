/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.customer.Customer;
import model.customer.CustomerDAO;

/**
 *
 * @author This PC
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet", "/register"})
public class RegisterServlet extends HttpServlet {
    private static final CustomerDAO customerDAO;
    
    static {
        customerDAO = new CustomerDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {      
        System.out.println("Served at [" + getServletInfo() + "]");
        HttpSession session = request.getSession(true);        
        
        String charEncoding = "UTF-8";
        request.setCharacterEncoding(charEncoding);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String fullname = request.getParameter("fullname");
        String userAddress = request.getParameter("userAddress");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");

        boolean testExistUsername = customerDAO.checkExistUsername(username);
        boolean testConfirmPassword = checkConfirmPassword(password, confirmPassword);
        boolean testPhoneNumber = webpage_tools.CheckInputPhoneNumber.check(phoneNumber);
        
        if(!testExistUsername && testConfirmPassword && testPhoneNumber)
        {
            Customer customer = new Customer(0, username, email, password, fullname, phoneNumber, userAddress);
            customerDAO.insert(customer);
            
            //Set customer to Session for other webapp functions
            session.setAttribute("customer", customer);
            session.setAttribute("registerMessage", "ĐĂNG KÝ TÀI KHOẢN THÀNH CÔNG");
            
            response.sendRedirect(webpage_tools.WebPageEnum.ROOT.getURL());
        }
        else {
            setFormAttributesWithouRetype(session, username, fullname, userAddress, email, phoneNumber);
            if(testExistUsername)
            {
                explicitSetFormAttributesWithouRetype(session, "username", "");
                session.setAttribute("errorUsername", "TÊN ĐĂNG NHẬP ĐÃ TỒN TẠI VUI LÒNG CHỌN TÊN KHÁC");
            }
            
            if(testConfirmPassword == false) {
                session.setAttribute("errorPassword", "MẬT KHẨU NHẬP LẠI KHÔNG TRÙNG KHỚP, VUI LÒNG NHẬP LẠI");
            }
            
            if(testPhoneNumber == false)
            {
                explicitSetFormAttributesWithouRetype(session, "phoneNumber", "");
                session.setAttribute("errorPhone", "SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ, VUI LÒNG NHẬP LẠI");
            }
            
            response.sendRedirect(webpage_tools.WebPageEnum.REGISTER_PAGE.getURL());
        }
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

    public boolean checkConfirmPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
    
    /**
     * This method help user not to retype again their information if some thing error happened
     * @param session 
     */
    public void setFormAttributesWithouRetype(HttpSession session,
            String username,
            String fullname,
            String userAddress,
            String email,
            String phoneNumber)
    {
        session.removeAttribute("errorUsername");
        session.removeAttribute("errorPassword");
        session.setAttribute("username", username);
        session.setAttribute("fullname", fullname);
        session.setAttribute("userAddress", userAddress);
        session.setAttribute("email", email);
        session.setAttribute("phoneNumber", phoneNumber);
    }
    
    /**
     * This method help user not to retype again their information if some thing error happened
     * <br> explicitly set the value
     * @param session
     * @param setName
     * @param setValue 
     */
    public void explicitSetFormAttributesWithouRetype(HttpSession session, String setName, String setValue)
    {
        session.setAttribute(setName, setValue);
    }
}
