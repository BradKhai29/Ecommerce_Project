package controller.user;

import controller.CookieEnum;
import controller.SupportEnum;
import controller.TimeEnum;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.customer.Customer;
import model.customer.CustomerDAO;
import webpage_tools.MessageEnum;

/**
 *
 * This Servlet checks login information
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet", "/login"})
public class LoginServlet extends HttpServlet {

    private static final CustomerDAO customerDAO;

    static {
        customerDAO = new CustomerDAO();
        new HashMap<>();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Served at [" + getServletInfo() + "]");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        Optional<Customer> customer = customerDAO.authenticate(username, password);

        if (customer.isPresent()) 
        {
            if (remember != null) 
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

    private void createRememberUserCookie(HttpServletResponse response, Customer customer) 
    {
        String userHashCode = Integer.toString(customer.hashCode());
        Cookie rememberUserCookie = new Cookie(CookieEnum.REMEMBER_USER_COOKIE.getName(), userHashCode);

        //Add this cookie to response for later retrieve
        rememberUserCookie.setMaxAge(TimeEnum.REMEMBER_USER_COOKIE_TIME.getValue());
        response.addCookie(rememberUserCookie);

        //Add this user to rememeber maps for later auto-login function with rememberUserCookie
        if (RememberUserManager.get(userHashCode).isEmpty()) {
            RememberUserManager.add(userHashCode, customer);
        }
    }
}
