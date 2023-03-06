package controller;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webpage_tools.*;

/**
 *
 * @author This PC
 */
@WebServlet(name = "RootController", urlPatterns = {"/RootController", "/root"})
public class RootController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Served at [" + getServletInfo() + "]");
        initURLs(request);
        response.sendRedirect(ServletEnum.PRODUCT_LOAD.getURL());
    }
    
    private void initURLs(HttpServletRequest request)
    {
        ServletContext application = request.getServletContext();
        application.setAttribute("root", application.getContextPath());
        
        //Init the URLs for web page
        String loginPage = WebPageEnum.LOGIN_PAGE.getURL();
        String registerPage = WebPageEnum.REGISTER_PAGE.getURL();
        String temporaryCartPage = WebPageEnum.TEMP_CART.getURL();
        String invoicePage = WebPageEnum.INVOICE_HISTORY.getURL();
        String userDetailPage = WebPageEnum.USER_DETAIL.getURL();
        String productDetailPage = WebPageEnum.PRODUCT_DETAIL.getURL();
        
        //Init URLs for Serlvet
        String userServlet = ServletEnum.USER.getURL();
        
        //Set URLS for applicationScope
        application.setAttribute("login", loginPage);
        application.setAttribute("register", registerPage);
        application.setAttribute("cart", temporaryCartPage);
        application.setAttribute("userDetail", userDetailPage);
        application.setAttribute("productDetail", productDetailPage);
        application.setAttribute("user", userServlet);
        application.setAttribute("invoicePage", invoicePage);
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

}
