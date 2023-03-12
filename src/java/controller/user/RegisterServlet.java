/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import controller.support.SupportEnum;
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
import webpage_tools.MessageEnum;
import webpage_tools.PrintTools;
import webpage_tools.WebPageEnum;

/**
 *
 * @author This PC
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet", "/register"})
public class RegisterServlet extends HttpServlet {
    private static final CustomerDAO customerDAO;
    private static WebPageEnum registerPage = WebPageEnum.REGISTER_PAGE;
    
    static {
        customerDAO = new CustomerDAO();
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Served at [" + getServletName()+ "]");
        request.setCharacterEncoding(PrintTools.getUTF8());
        HttpSession session = request.getSession();
        
        String step = request.getParameter("register").toLowerCase();
        switch (step) 
        {
            case "step1":
                processStep1(request, response);
                break;
            case "step2":
                processStep2(request, response);
                break;
            default:
                session.removeAttribute("step2");
                response.sendRedirect(registerPage.getURL());
        }
    }
    
    /**
     * Step 1 include info about fullname, address, email, phone number
     * <br>Have checking phone number
     * @param request
     * @param response
     * @throws IOException 
     */
    private void processStep1(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        System.out.println("Process step 1");
        HttpSession session = request.getSession();
        Customer customer = (Customer)session.getAttribute(SupportEnum.REGISTER_CUSTOMER.getName());
        if(customer == null) customer = new Customer();
        
        String fullname = request.getParameter("fullname");
        String userAddress = request.getParameter("userAddress");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        
        customer.setFullname(fullname);
        customer.setUserAddress(userAddress);
        customer.setEmail(email);
        
        boolean testPhoneNumber = webpage_tools.CheckInputPhoneNumber.check(phoneNumber);
        if(testPhoneNumber)
        {
            session.setAttribute("step2", "");
            customer.setPhoneNumber(phoneNumber);
            session.removeAttribute(MessageEnum.INVALID_PHONENUMBER.getName());
        }
        else session.setAttribute(MessageEnum.INVALID_PHONENUMBER.getName(), MessageEnum.INVALID_PHONENUMBER.getMessage());
        
        session.setAttribute(SupportEnum.REGISTER_CUSTOMER.getName(), customer);
        response.sendRedirect(registerPage.getURL());
    }
    
    private void processStep2(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        System.out.println("Process step 2");
        HttpSession session = request.getSession();
        Customer customer = (Customer)session.getAttribute(SupportEnum.REGISTER_CUSTOMER.getName());
    
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        Object TempCart = session.getAttribute(SupportEnum.TEMPORARY_CART.getName());
        
        boolean testExistUsername = customerDAO.checkExistUsername(username);
        boolean testConfirmPassword = checkConfirmPassword(password, confirmPassword);
        boolean haveTempCart = TempCart != null;
        
        boolean allTest = !testExistUsername && testConfirmPassword;
        MessageEnum message;
        if(testExistUsername)
        {
            message = MessageEnum.EXIST_USERNAME;
            session.setAttribute(message.getName(), message.getMessage());
        }
        
        if(testConfirmPassword == false)
        {
            message = MessageEnum.CONFIRM_PASSWORD_ERROR;
            session.setAttribute(message.getName(), message.getMessage());
        }
        
        if (allTest) {
            message = MessageEnum.REGISTER_SUCCESS;
            customer.setUsername(username);
            customer.setPasswd(password);
            customerDAO.insert(customer);
            
            session.removeAttribute("step2");
            session.removeAttribute(SupportEnum.REGISTER_CUSTOMER.getName());
            session.setAttribute(SupportEnum.CUSTOMER.getName(), customer);
            
            //Send to save temporary cart servlet
            if(haveTempCart) response.sendRedirect(webpage_tools.ServletEnum.TEMP_CART_SAVE.getURL());
            else response.sendRedirect(webpage_tools.WebPageEnum.HOME.getURL());
        }
        else response.sendRedirect(registerPage.getURL());
    }
    
    public boolean checkConfirmPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
