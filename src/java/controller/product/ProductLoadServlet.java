package controller.product;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.product.Product;
import model.product.ProductDAO;

/**
 *
 * This Servlet will load all products from Database to the Application
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet", "/productLoad"})
public class ProductLoadServlet extends HttpServlet {    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoadProduct(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoadProduct(request, response);
    }
    
    /**
     * Load the products from Database and set it to application scope
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    protected void LoadProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        System.out.println("Served at [" + getServletName().concat("]"));
        
        //Get the servlet context
        ServletContext application = getServletContext();    
        //get Products from productDAO
        Map<Integer, Product> products = new ProductDAO().getAll(true);
        //set products as application scope
        application.setAttribute("products", products);
        
        response.sendRedirect(webpage_tools.WebPageEnum.HOME.getURL());
    }
}
