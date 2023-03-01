/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
 * @author This PC
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet", "/product"})
public class ProductServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Serve at Product servlet");
        
        ServletContext servletContext = getServletContext();
        
        Map<Integer, Product> products = (Map<Integer, Product>) servletContext.getAttribute("products");
        if(products == null) 
        {
            products = new ProductDAO().getAll();
            servletContext.setAttribute("products", products);
        }
        
        servletContext.setAttribute("root", servletContext.getContextPath());
        
        response.sendRedirect(webpage_tools.WebPageEnum.HOME.getURL());
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return getServletName();
    }

}
