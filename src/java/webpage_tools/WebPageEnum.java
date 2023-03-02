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
    HOME("home.jsp"),
    ERROR_404("error404.jsp"),
    PRODUCT_DETAIL("product_detail.jsp");
    
    private static String contextPath = "/A_Ecommerce_Project/";
    private String URL;
    
    private WebPageEnum() {
    }

    private WebPageEnum(String pageURL) {
        this.URL = pageURL;
    }

    public String getURL() {
        return contextPath.concat(URL);
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
    
    @Override
    public String toString() {
        return getURL();
    }
}
