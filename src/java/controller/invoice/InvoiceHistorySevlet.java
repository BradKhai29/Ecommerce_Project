/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.invoice;

import controller.support.SupportEnum;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.customer.Customer;
import model.invoice.Invoice;

@WebServlet(name = "InvoiceHistorySevlet", urlPatterns = {"/InvoiceHistorySevlet", "/invoiceHistory"})
public class InvoiceHistorySevlet extends HttpServlet {
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
        System.out.println("Served at [" + getServletName() + "]");
        HttpSession session = request.getSession();
        Customer user = (Customer)session.getAttribute(SupportEnum.CUSTOMER.getName());
        
        SupportEnum addInvoices = SupportEnum.ADD_INVOICES_HISTORY_CHECKPOINT;
        Object addInvoiceHistory = session.getAttribute(addInvoices.getName());
        
        boolean isLogin = user != null;
        boolean isAdd = addInvoiceHistory != null;
        boolean haveNewPayment = user.HaveNewPayment();
        
        if (isLogin) 
        {
            Map<Integer, Invoice> invoices;
            if (!isAdd) 
            {
                invoices = user.getInvoices();
                session.setAttribute(SupportEnum.INVOICE_HISTORY.getName(), invoices);      
                
                //Add checkpoint to prevent the invoice list being duplicated in loading
                session.setAttribute(addInvoices.getName(), "checkpoint");
            }
            if (isAdd) 
            {
                if(haveNewPayment) 
                {
                    invoices = user.getInvoices();
                    session.setAttribute(SupportEnum.INVOICE_HISTORY.getName(), invoices);   
                }
            }
            response.sendRedirect(webpage_tools.WebPageEnum.INVOICE_HISTORY.getURL());
        }
        else response.sendRedirect(webpage_tools.WebPageEnum.HOME.getURL());
    }
}
