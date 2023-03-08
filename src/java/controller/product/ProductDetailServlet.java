/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.product.*;
import webpage_tools.*;

/**
 *
 * @author This PC
 */
@WebServlet(name = "ProductDetailServlet", urlPatterns = {"/ProductDetailServlet", "/productDetail"})
public class ProductDetailServlet extends HttpServlet {
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
        String productID = request.getParameter("productID");
        ProductDAO productDAO = new ProductDAO();
        Optional<Product> productOptional = productDAO.get(Integer.parseInt(productID));
        
        Product product = productOptional.orElse(new Product());
        request.getSession().setAttribute("product", product);
        
        response.sendRedirect(WebPageEnum.PRODUCT_DETAIL.getURL());
    }
}
