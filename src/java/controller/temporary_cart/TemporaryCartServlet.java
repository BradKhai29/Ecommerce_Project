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
import model.product.*;
import webpage_tools.WebPageEnum;

/**
 * @code This Servlet is used for create temporaryCart and send temporaryCart cookie
 */
@WebServlet(name = "ProductCartServlet", urlPatterns = {"/ProductCartServlet", "/productCart"})
public final class TemporaryCartServlet extends HttpServlet {

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
        System.out.println("Served at [" + getServletName() + "]");
        
        HttpSession session = request.getSession(true);
        String addToCart = request.getParameter("addToCart");
        
        TemporaryCart temporaryCart = getTemporaryCart(session);
        AddToCart(request, temporaryCart);
        
        //get user stored in session
        Customer user = (Customer) session.getAttribute(SupportEnum.CUSTOMER.getName());
        boolean isLogin = user != null;

        if (isLogin) {
            processWithLoginUser(temporaryCart, user);
        } else {
            processWithoutLoginUser(session, response, temporaryCart);
        }

        //Set temporary cart to Session
        session.setAttribute(SupportEnum.TEMPORARY_CART.getName(), temporaryCart);
        
        if(addToCart != null) 
        {
            if(addToCart.equals("productDetail"));
            else response.sendRedirect(WebPageEnum.HOME.getURL());
        }
        else response.sendRedirect(WebPageEnum.TEMP_CART.getURL());
    }
    
    /**
     * Process add to cart action
     * @param request
     * @param temporaryCart 
     */
    private void AddToCart(HttpServletRequest request, TemporaryCart temporaryCart)
    {
        int productID = Integer.parseInt(request.getParameter("productID"));
        
        Optional<Product> product = productDAO.get(productID);
        temporaryCart.add(product.get(), 1);
    }

    /**
     * Return the current temporary cart in the session. If the TemporaryCart is <code style='color:red'>NULL</code>, create new one
     * @param session
     * @param user
     * @return
     */
    private TemporaryCart getTemporaryCart(HttpSession session) {
        TemporaryCart temporaryCart = (TemporaryCart) session.getAttribute(SupportEnum.TEMPORARY_CART.getName());
        
        boolean isExist = temporaryCart != null;
        if (isExist) {
            return temporaryCart;
        }

        return TemporaryCart.createNew();
    }

    private void processWithLoginUser(TemporaryCart temporaryCart, Customer user) 
    {
        TemporaryCartManager.add(user.getUsername(), temporaryCart);
    }

    private void processWithoutLoginUser(HttpSession session, HttpServletResponse response, TemporaryCart temporaryCart) 
    {
        boolean noCheckPoint = session.getAttribute(SupportEnum.ADD_TEMP_CART_COOKIE_CHECKPOINT.getName()) == null;
        if (noCheckPoint) 
        {
            String cookieValue = Integer.toString(temporaryCart.hashCode());
            CookieSupportServlet.addCookie(response, CookieEnum.TEMPORARY_CART_COOKIE, cookieValue);
            
            session.setAttribute(SupportEnum.ADD_TEMP_CART_COOKIE_CHECKPOINT.getName(), "check");
            TemporaryCartManager.add(cookieValue, temporaryCart);
        }
    }
}
