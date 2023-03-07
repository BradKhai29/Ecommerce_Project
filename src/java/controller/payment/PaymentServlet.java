/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.payment;

import controller.support.SupportEnum;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.customer.Customer;
import model.invoice.Invoice;
import model.invoice.InvoiceDAO;
import model.temporary_cart.TemporaryCart;
import webpage_tools.*;

@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet", "/payment"})
public class PaymentServlet extends HttpServlet {
    private static InvoiceDAO invoiceDAO;
    
    static {
        invoiceDAO = new InvoiceDAO();
    }
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer user = (Customer)session.getAttribute(SupportEnum.CUSTOMER.getName());
        TemporaryCart temporaryCart = (TemporaryCart)session.getAttribute(SupportEnum.TEMPORARY_CART.getName());
        
        //Create message
        MessageEnum message;
        
        if(user == null){
            message = MessageEnum.LOGIN_REQUIRED;
            session.setAttribute(message.getName(), message.getMessage());
            response.sendRedirect(WebPageEnum.LOGIN_PAGE.getURL());
        }
        
        else{
            message = MessageEnum.PAYMENT_SUCCESS;            
            session.setAttribute(message.getName(), message.getMessage());
            
            //Process the payment, create new Invoice to save to database
            processPayment(temporaryCart);
            temporaryCart.clear();
            
            response.sendRedirect(webpage_tools.WebPageEnum.TEMP_CART.getURL());
        }
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
    
    private void processPayment(TemporaryCart temporaryCart)
    {
        Invoice invoice = new Invoice(temporaryCart);
        invoiceDAO.insert(invoice);
    }

}
