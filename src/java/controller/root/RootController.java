package controller.root;

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
            throws ServletException, IOException 
    {
        System.out.println("Served at [" + getServletName()+ "]");
        initURLs(request);
        response.sendRedirect(ServletEnum.PRODUCT.getURL());
    }
    
    private void initURLs(HttpServletRequest request)
    {
        ServletContext application = request.getServletContext();
        //Init the URLs for web page
        String adminPage = WebPageEnum.ADMIN_PAGE.getURL();
        String loginPage = WebPageEnum.LOGIN_PAGE.getURL();
        String registerPage = WebPageEnum.REGISTER_PAGE.getURL();
        String temporaryCartPage = WebPageEnum.TEMP_CART.getURL();
        
        //Init URLs for Serlvet
        String invoiceHistoryServlet = ServletEnum.INVOICE_HISTORY.getURL();
        String userServlet = ServletEnum.USER.getURL();
        String productServlet = ServletEnum.PRODUCT.getURL();
        
        //Set URLS for applicationScope
        application.setAttribute("root", application.getContextPath());
        application.setAttribute("adminPage", adminPage);
        application.setAttribute("login", loginPage);
        application.setAttribute("register", registerPage);
        application.setAttribute("cart", temporaryCartPage);
        application.setAttribute("productServlet", productServlet);
        application.setAttribute("user", userServlet);
        application.setAttribute("invoiceHistory", invoiceHistoryServlet);
    }
}
