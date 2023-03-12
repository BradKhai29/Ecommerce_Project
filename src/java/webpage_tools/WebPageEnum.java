/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webpage_tools;

/**
 *
 * @author This PC
 */
public enum WebPageEnum {
    ROOT(""),
    HOME(""),
    ERROR_404("error404.jsp"),
    PRODUCT_DETAIL("product_detail_page"),
    LOGIN_PAGE("loginPage"),
    REGISTER_PAGE("registerPage"),
    TEMP_CART("product_cart_page"),
    INVOICE_HISTORY("invoice_history_page"),
    USER_DETAIL("user_detail_page", "user_detail.jsp"),
    ADMIN_PAGE("admin_page"),
    ADMIN_LOGIN("admin_login"),
    ADMIN_SETTING_PAGE("admin_setting_page"),
    ADMIN_PRODUCTS_PAGE("products"),
    ADMIN_PRODUCT_MODIFIED_PAGE("product_modified");
    
    private static String contextPath = "/A_Ecommerce_Project/";
    private String JSP_Page;
    private String URL;


    private WebPageEnum(String pageURL) {
        this.URL = pageURL;
    }
    
    private WebPageEnum(String pageURL, String JSP_Page) {
        this.URL = pageURL;
        this.JSP_Page = JSP_Page;
    }

    public String getURL() {
        return contextPath.concat(URL);
    }
    
    public String getPage() {
        return contextPath.concat(JSP_Page);
    }
    
    /**
     * Return a URL with given prefix
     * <br>Each prefix will be add before the current URL to generate the final URL
     * Example: WebPageEnum.ROOT.getURL(FolderEnum.VIEW, FolderEnum.USER) = /A_Ecommerce_Project/view/user/
     * @param prefixURL : array of FolderEnum
     * @return 
     */
    public String getURL(FolderEnum... prefixURL) {
        StringBuffer URLGenerater = new StringBuffer(contextPath);
        
        for(FolderEnum prefix : prefixURL)
        {
            URLGenerater.append(prefix);
        }
            
        URLGenerater.append(this.URL);
        return URLGenerater.toString();
    }
    
    public String getPage(FolderEnum... prefixURL) {
        StringBuffer URLGenerater = new StringBuffer(contextPath);
        
        for(FolderEnum prefix : prefixURL)
        {
            URLGenerater.append(prefix);
        }
            
        URLGenerater.append(this.JSP_Page);
        return URLGenerater.toString();
    }
    
    @Override
    public String toString() {
        return getURL();
    }
}
