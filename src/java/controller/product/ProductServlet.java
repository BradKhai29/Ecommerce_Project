package controller.product;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.product.Product;
import model.product.ProductDAO;
import webpage_tools.PrintTools;
import webpage_tools.WebPageEnum;

@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {
    private static final ProductDAO ProductDAO;
    static {
        ProductDAO = new ProductDAO();
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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Served at [" + getServletName().concat("]"));
        request.setCharacterEncoding(PrintTools.getUTF8());
        
        String command = request.getParameter("command");
        ProductCommandEnum commandEnum = ProductCommandEnum.getCommandEnum(command);
        
        switch (commandEnum) {
            case ADD_PRODUCT:
                AddProduct(request, response);
                break;
            case DETAIL_PRODUCT:
                DetailProduct(request, response);
                break;
            case DETAIL_PRODUCT_WITH_ADMIN_PERMISSION:
                DetailProductWithAdminPermission(request, response);
                break;
            case UPDATE_PRODUCT_INFO:
                UpdateProduct(request, response);
                break;
            case CONFIRM_UPDATE:
                ConfirmUpdateProduct(request, response);
                break;
            case DELETE_PRODUCT:
                DeleteProduct(request, response);
                break;
            default:
                LoadProduct(request, response);
        }
    }
    
    private boolean AdminNonNull(HttpSession session)
    {
        Object admin = session.getAttribute("admin");
        boolean adminNotNull = Objects.nonNull(admin);
        return adminNotNull;
    }
    
    protected void LoadProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        System.out.println("Get Load all Product");
        //Get the servlet context
        ServletContext application = getServletContext();    
        //get Products from productDAO
        Map<Integer, Product> products = ProductDAO.getAll(true);
        //set products as application scope
        application.setAttribute("products", products);
        
        response.sendRedirect(webpage_tools.WebPageEnum.HOME.getURL());
    }
    
    protected void AddProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {   
        System.out.println("Add new Product from Admin");
    }
    
    protected void DetailProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String productID = request.getParameter("productID");
        System.out.println("Get product detail with ID = " + productID);
        Optional<Product> productOptional = ProductDAO.get(Integer.parseInt(productID));
        
        Product product = productOptional.orElse(new Product());
        request.getSession().setAttribute("product", product);
        
        response.sendRedirect(WebPageEnum.PRODUCT_DETAIL.getURL());
    }
    
    protected void DetailProductWithAdminPermission(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        boolean adminNotNull = AdminNonNull(session);
        
        if (adminNotNull) {
            session.removeAttribute(ProductCommandEnum.UPDATE_PRODUCT_INFO.getCommand());
            
            String productID = request.getParameter("productID");
            System.out.println("Get product detail with ID = " + productID);
            
            Optional<Product> productOptional = ProductDAO.get(Integer.parseInt(productID));
            Product product = productOptional.orElse(new Product());
            session.setAttribute("product", product);

            response.sendRedirect(WebPageEnum.ADMIN_PRODUCT_MODIFIED_PAGE.getURL());
        }
        else response.sendRedirect(WebPageEnum.ADMIN_LOGIN.getURL());
    }
    
    protected void UpdateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        boolean adminNotNull = AdminNonNull(session);
        
        if (adminNotNull) {
            String productID = request.getParameter("productID");
            System.out.println("Get update product info with ID = " + productID);
            
            session.setAttribute(ProductCommandEnum.UPDATE_PRODUCT_INFO.getCommand(), "update");
            response.sendRedirect(WebPageEnum.ADMIN_PRODUCT_MODIFIED_PAGE.getURL());
        }
        else response.sendRedirect(WebPageEnum.ADMIN_LOGIN.getURL());
    }
    
    protected void ConfirmUpdateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        boolean adminNotNull = AdminNonNull(session);
        
        
        if (adminNotNull) {
            String productID = request.getParameter("productID");
            System.out.println("Confirm update product Info ID = " + productID);
            
            //Get product from the database
            Optional<Product> productOptional = ProductDAO.get(Integer.parseInt(productID));
            Product product = productOptional.orElse(Product.createNew());
            
            //Collecting data getting from the Form and update them into current Product
            CollectFormData(request, product);
            processUpdate(product);
            
            session.removeAttribute(ProductCommandEnum.UPDATE_PRODUCT_INFO.getCommand());
            response.sendRedirect(WebPageEnum.ADMIN_PRODUCT_MODIFIED_PAGE.getURL());
        }
        else response.sendRedirect(WebPageEnum.ADMIN_LOGIN.getURL());
    }
    
    private void CollectFormData(HttpServletRequest request, Product product)
    {
        String productName = request.getParameter("productName");
        String typeID = request.getParameter("productType");
        String productDetails = request.getParameter("details");
        String newPrice = request.getParameter("price");
        String productStatusCode = request.getParameter("productStatus");
        
        //Update collected data to Product
        boolean isSamePrice = newPrice.equals(String.valueOf(product.getPrice()));
        product.setProductName(productName);
        product.setTypeID(Integer.parseInt(typeID));
        product.setDetails(productDetails);
        
        if(isSamePrice == false) product.setPrice(Integer.parseInt(newPrice));
        product.setProductStatus(Integer.parseInt(productStatusCode));
    }
    
    private void processUpdate(Product updatedProduct)
    {
        ProductDAO.update(updatedProduct);
    }
    
    protected void DeleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        boolean adminNotNull = AdminNonNull(session);
        
        if (adminNotNull) {
            String productID = request.getParameter("productID");
            System.out.println("Delete product ID = " + productID);
            
            Optional<Product> productOptional = ProductDAO.get(Integer.parseInt(productID));
            Product product = productOptional.orElse(Product.createNew());
            product.setProductStatus(4);
            ProductDAO.delete(product.getProductID());
            
            session.removeAttribute(ProductCommandEnum.UPDATE_PRODUCT_INFO.getCommand());
            response.sendRedirect(WebPageEnum.ADMIN_PRODUCT_MODIFIED_PAGE.getURL());
        }
        else response.sendRedirect(WebPageEnum.ADMIN_LOGIN.getURL());
    }
}
