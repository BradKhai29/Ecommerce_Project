/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.customer.Customer;

/**
 *
 * This servlet will save the temporary cart with key = username.
 * <br>If user did add to cart without login, but then login to buy.
 * <br>This servlet will save that cart to cart manager with key = username
 */
@WebServlet(name = "SaveTemporaryCartServlet", urlPatterns = {"/SaveTemporaryCartServlet", "/saveTempCart"})
public class SaveTemporaryCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Served at [" + getServletName() + "]");
        HttpSession session = request.getSession();
        
        Customer user = (Customer)session.getAttribute(SupportEnum.CUSTOMER.getName());
        TemporaryCart temporaryCart = (TemporaryCart)session.getAttribute(SupportEnum.TEMPORARY_CART.getName());
        
        if(user != null && temporaryCart != null)
        {
            saveTemporaryCartByUsername(request, response, user, temporaryCart);
        }
        response.sendRedirect(webpage_tools.ServletEnum.TEMP_CART_LOAD.getURL());
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

    /**
     * If user add to cart before login, if they login to buy, then save cart status
     * @param response
     * @param user
     * @param temporaryCart 
     */
    private void saveTemporaryCartByUsername(HttpServletRequest request, HttpServletResponse response
            , Customer user
            , TemporaryCart temporaryCart)
    {
        //Remove the temporaryCart cookie
        CookieEnum cookieEnum = CookieEnum.TEMPORARY_CART_COOKIE;
        String cookieValue = CookieSupportServlet.processCookie(request, response, cookieEnum, true);
        
        //remove mapping
        TemporaryCartManager.remove(cookieValue);
        
        //Save user obj to this TemporaryCart
        temporaryCart.setUser(Optional.of(user));
        //Map the current temporary cart with new key = username
        TemporaryCartManager.add(user.getUsername(), temporaryCart);
    }
}
