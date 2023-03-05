package controller.temporary_cart;

import model.temporary_cart.TemporaryCartManager;
import model.temporary_cart.TemporaryCart;
import controller.SupportEnum;
import controller.TimeEnum;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.customer.Customer;
import model.product.*;
import webpage_tools.WebPageEnum;

/**
 * @code This Servlet is used for create temporaryCart and send temporaryCart cookie
 */
@WebServlet(name = "ProductCartServlet", urlPatterns = {"/ProductCartServlet", "/productCart"})
public final class ProductCartServlet extends HttpServlet {

    private static final ProductDAO productDAO;

    static {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(webpage_tools.WebPageEnum.HOME.getURL());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Served at [" + getServletInfo() + "]");
        HttpSession session = request.getSession(true);
        
        String addToCart = request.getParameter("addToCart");
        
        //get user
        Customer user = (Customer) session.getAttribute(SupportEnum.CUSTOMER.getName());
        boolean isLogin = user != null;

        //get temp cart
        TemporaryCart temporaryCart = getTemporaryCart(session, user);

        int productID = Integer.parseInt(request.getParameter("productID"));
        Optional<Product> product = productDAO.get(productID);
        temporaryCart.add(product.get(), 1);

        if (isLogin) {
            processWithLoginUser(session, response, temporaryCart, user);
        } else {
            processWithoutLoginUser(session, response, temporaryCart);
        }

        session.setAttribute(SupportEnum.TEMPORARY_CART.getName(), temporaryCart);
        if(addToCart != null) response.sendRedirect(WebPageEnum.HOME.getURL());
        else response.sendRedirect(WebPageEnum.TEMP_CART.getURL());
    }

    @Override
    public String getServletInfo() {
        return getServletName();
    }

    /**
     * Return the current temporary cart in the session
     * <br> if <code>null</code>, create one
     *
     * @param session
     * @param user
     * @return
     */
    private TemporaryCart getTemporaryCart(HttpSession session, Customer user) {
        TemporaryCart temporaryCart = (TemporaryCart) session.getAttribute(SupportEnum.TEMPORARY_CART.getName());
        if (temporaryCart == null) {
            return new TemporaryCart(user);
        }

        return temporaryCart;
    }

    /**
     * This method is support for non-login user
     * <br>Create temporary Cart cookie with value = temporaryCart hashcode
     * @param response
     * @param temporaryCart
     * @param username
     */
    private void createTemporaryCartCookie(HttpSession session, HttpServletResponse response, TemporaryCart temporaryCart) {
        if (session.getAttribute(SupportEnum.ADD_TEMP_CART_COOKIE_CHECKPOINT.getName()) != null) {
            return;
        }
        
        //Get hashcode as value for this cookie
        String cookieValue = Integer.toString(temporaryCart.hashCode());
        //Create cookie
        Cookie temporaryCartCookie = new Cookie(SupportEnum.TEMPORARY_CART_COOKIE.getName(), cookieValue);
        temporaryCartCookie.setMaxAge(TimeEnum.REMEMBER_USER_COOKIE_TIME.getValue());

        //add cookie
        response.addCookie(temporaryCartCookie);

        session.setAttribute(SupportEnum.ADD_TEMP_CART_COOKIE_CHECKPOINT.getName(), "check");
    }

    private void processWithLoginUser(HttpSession session, HttpServletResponse response, TemporaryCart temporaryCart, Customer user) {
        //Add to TemporaryCart manager
        TemporaryCartManager.add(user.getUsername(), temporaryCart);
    }

    private void processWithoutLoginUser(HttpSession session, HttpServletResponse response, TemporaryCart temporaryCart) {
        //Create cookie
        createTemporaryCartCookie(session, response, temporaryCart);

        //Add to TemporaryCart manager
        String key = Integer.toString(temporaryCart.hashCode());
        TemporaryCartManager.add(key, temporaryCart);
    }
}
