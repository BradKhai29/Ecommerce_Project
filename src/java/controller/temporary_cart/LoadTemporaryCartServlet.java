package controller.temporary_cart;

import controller.support.CookieEnum;
import controller.support.CookieSupportServlet;
import model.temporary_cart.TemporaryCartManager;
import model.temporary_cart.TemporaryCart;
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
import webpage_tools.MessageEnum;

/**
 * Load the Temporary Cart corresponding to these situations:
 * <br>> cookie value : if user not login
 * <br>> username : if user login
 */
@WebServlet(name = "LoadTemporaryCartServlet", urlPatterns = {"/LoadTemporaryCartServlet", "/loadTempCart"})
public class LoadTemporaryCartServlet extends HttpServlet {
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
        System.out.println("Served at [" + getServletInfo() + "]");
        
        HttpSession session = request.getSession(true);
        Object requiredLoginMessage = session.getAttribute(MessageEnum.LOGIN_REQUIRED.getName());

        Customer user = (Customer) session.getAttribute(SupportEnum.CUSTOMER.getName());
        boolean isLogin = user != null;
        
        if (isLogin) {
            LoadTempCartFromUser(session, user);
        } 
        else {
            LoadTempCartFromCookie(request, response);
        }

        boolean haverequiredLoginMessage = requiredLoginMessage != null;

        if (haverequiredLoginMessage) {
            session.removeAttribute(MessageEnum.LOGIN_REQUIRED.getName());
            response.sendRedirect(webpage_tools.WebPageEnum.TEMP_CART.getURL());
        } else {
            response.sendRedirect(webpage_tools.WebPageEnum.HOME.getURL());
        }
    }

    private void LoadTempCartFromCookie(HttpServletRequest request, HttpServletResponse response) 
    {
        HttpSession session = request.getSession(true);

        //Create TEMPORARY_CART_COOKIE enum
        CookieEnum cookieEnum = CookieEnum.TEMPORARY_CART_COOKIE;
        
        //get cookieValue to load again the temporaryCart
        String cookieValue = CookieSupportServlet.processCookie(request, response, cookieEnum, false);
        
        //Load the temporaryCart from TemporaryCartManager
        Optional<TemporaryCart> temporaryCart = TemporaryCartManager.get(cookieValue);
        
        if (temporaryCart.isPresent()) {
            System.out.println("Exist temporary Cart with id [" + cookieValue + ']');
            session.setAttribute(SupportEnum.TEMPORARY_CART.getName(), temporaryCart.get());
        }
    }

    private void LoadTempCartFromUser(HttpSession session, Customer user) 
    {
        String username = user.getUsername();
        //Load temporary cart with key = username
        Optional<TemporaryCart> temporaryCart = TemporaryCartManager.get(username);

        if (temporaryCart.isPresent()) {
            System.out.println("Exist temporary Cart with key [" + username + "]");
            session.setAttribute(SupportEnum.TEMPORARY_CART.getName(), temporaryCart.get());
        }
    }
}
