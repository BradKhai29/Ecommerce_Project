package controller.temporary_cart;

import model.temporary_cart.TemporaryCart;
import controller.SupportEnum;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ChangeQuantityServlet", urlPatterns = {"/ChangeQuantityServlet", "/changeQuantity"})
public class ChangeQuantityServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        TemporaryCart temporaryCart = (TemporaryCart)session.getAttribute(SupportEnum.TEMPORARY_CART.getName());
        String plus = request.getParameter("plus");
        String minus = request.getParameter("minus");
        String delete = request.getParameter("delete");
        int productID = Integer.parseInt(request.getParameter("productID"));
        int quantity = 1;
        try {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } catch (Exception e) {
        }
        
        if(temporaryCart != null)
        {
            boolean isLargerThan1 = quantity > 1;
            boolean isPlus = plus != null;
            boolean isMinus = minus != null;
            boolean isDelete = delete != null;
            
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
        }
        
        response.sendRedirect(webpage_tools.WebPageEnum.TEMP_CART.getURL());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(webpage_tools.WebPageEnum.HOME.getURL());
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
