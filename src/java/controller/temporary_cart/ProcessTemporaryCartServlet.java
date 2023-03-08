package controller.temporary_cart;

import model.temporary_cart.TemporaryCart;
import controller.support.SupportEnum;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ChangeQuantityServlet", urlPatterns = {"/ChangeQuantityServlet", "/changeQuantity"})
public class ProcessTemporaryCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(webpage_tools.WebPageEnum.HOME.getURL());
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Served at [" + getServletName() + "]");
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();        
        TemporaryCart temporaryCart = (TemporaryCart)session.getAttribute(SupportEnum.TEMPORARY_CART.getName());
        
        processCartAction(temporaryCart, request);
        
        response.sendRedirect(webpage_tools.WebPageEnum.TEMP_CART.getURL());
    }
    
    private void processCartAction(TemporaryCart temporaryCart, HttpServletRequest request)
    {
        //Get information section
        String plus = request.getParameter("plus");
        String minus = request.getParameter("minus");
        String delete = request.getParameter("delete");
        String clear = request.getParameter("clear");
        int productID = 0;
        int quantity = 1;
        try {
            productID = Integer.parseInt(request.getParameter("productID"));
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } catch (Exception e) {
        }
        
        //Process Action section
        if(temporaryCart != null)
        {
            boolean isLargerThan1 = quantity > 1;
            boolean isPlus = plus != null;
            boolean isMinus = minus != null;
            boolean isDelete = delete != null;
            boolean isClear = clear != null;
            
            if(isPlus) {
                System.out.println("INCREASE_QUANTITY");
                quantity++;
                temporaryCart.updateQuantity(productID, quantity);
            }
            
            if(isMinus) {
                System.out.println("DECREASE_QUANTITY");
                if(isLargerThan1) quantity--;
                temporaryCart.updateQuantity(productID, quantity);
            }
            
            if(isDelete){
                temporaryCart.remove(productID);
            }
            
            if(isClear){
                temporaryCart.clear();
            }
        }
    }
}
