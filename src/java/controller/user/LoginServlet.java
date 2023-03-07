package controller.user;

import controller.support.CookieEnum;
import controller.support.CookieSupportServlet;
import controller.support.SupportEnum;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.customer.Customer;
import model.customer.CustomerDAO;
import webpage_tools.MessageEnum;

/**
 *
 * This Servlet will handle login for Application
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet", "/login"})
public class LoginServlet extends HttpServlet {
    private static final CustomerDAO customerDAO;

    static {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(webpage_tools.WebPageEnum.LOGIN_PAGE.getURL());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processLogin(request, response);
    }
    
    protected void processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        System.out.println("Served at [" + getServletInfo() + "]");
        request.setCharacterEncoding(webpage_tools.PrintTools.getUTF8());

        HttpSession session = request.getSession(true);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        Optional<Customer> customer = customerDAO.Authenticate(username, password);
        boolean doRememeber = remember != null;

        if (customer.isPresent()) 
        {
            if (doRememeber) 
            {
                createRememberUserCookie(response, customer.get());
            }
            
            session.setAttribute(SupportEnum.CUSTOMER.getName(), customer.get());
            response.sendRedirect(webpage_tools.ServletEnum.TEMP_CART_SAVE.getURL());
        } 
        else 
        {
            MessageEnum message = MessageEnum.LOGIN_ERROR;
            session.setAttribute(message.getName(), message.getMessage());
            response.sendRedirect(webpage_tools.WebPageEnum.LOGIN_PAGE.getURL());
        }

    }

    private void createRememberUserCookie(HttpServletResponse response, Customer customer) 
    {
        String userHashCode = Integer.toString(customer.hashCode());
        CookieSupportServlet.addCookie(response, CookieEnum.REMEMBER_USER_COOKIE, userHashCode);
        //Add this user to rememeber maps for later auto-login function with rememberUserCookie
        if (RememberUserManager.get(userHashCode).isEmpty())
        {
            RememberUserManager.add(userHashCode, customer);
        }
    }
}
