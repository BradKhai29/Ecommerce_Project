/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webpage_tools;

/**
 *
 * @author This PC
 */
public enum ControllerEnum {
    ROOT(""),
    PRODUCT_LOAD("productLoad"),
    TEMP_CART_LOAD("loadTempCart"),
    TEMP_CART_SAVE("saveTempCart"),
    PAYMENT("payment"),
    LOGIN("login");
    
    private static String contextPath = "/A_Ecommerce_Project/";
    private String URL;
    

    private ControllerEnum() {
    }

    private ControllerEnum(String pageURL) {
        this.URL = pageURL;
    }

    public String getURL() {
        return contextPath.concat(URL);
    }
    
    public String getRefURL()
    {
        return "/".concat(URL);
    }
    
    @Override
    public String toString() {
        return getURL();
    }
}
