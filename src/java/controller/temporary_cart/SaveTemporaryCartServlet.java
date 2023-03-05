/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.temporary_cart;

import model.temporary_cart.TemporaryCartManager;
import model.temporary_cart.TemporaryCart;
import controller.SupportEnum;
import java.io.IOException;
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
        HttpSession session = request.getSession();
        
        Customer user = (Customer)session.getAttribute(SupportEnum.CUSTOMER.getName());
        TemporaryCart temporaryCart = (TemporaryCart)session.getAttribute(SupportEnum.TEMPORARY_CART.getName());
        
        if(user != null && temporaryCart != null)
        {
            saveTemporaryCartByUsername(request, response, user, temporaryCart);
        }
        response.sendRedirect(webpage_tools.ControllerEnum.TEMP_CART_LOAD.getURL());
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
        Cookie[] cookies = request.getCookies();
        for(int i = 0; i < cookies.length; i++)
        {
            Cookie existCookie = cookies[i];
            if(existCookie.getName().equals(SupportEnum.TEMPORARY_CART_COOKIE.getName()))
            {
                String cookieValue = existCookie.getValue();
                TemporaryCartManager.remove(cookieValue);
                
                existCookie.setMaxAge(0);
                response.addCookie(existCookie);
            }
        }
        
        //Map the current temporary cart with new key = username
        TemporaryCartManager.add(user.getUsername(), temporaryCart);
    }
}
