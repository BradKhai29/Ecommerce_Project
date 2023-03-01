/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webpage_tools;

/**
 *
 * @author This PC
 */
public enum FolderEnum {
    ROOT(""),
    VIEW("view"),
    ASSET("asset"),
    PRODUCT("product"),
    ADMIN("admin"),
    MAIN("main"),
    USER("user");
    
    
    private static String path = "/";
    private String URL;
    
    private FolderEnum() {
    }

    private FolderEnum(String pageURL) {
        this.URL = pageURL;
    }

    public String getURL() {
        return URL.concat(path);
    }

    @Override
    public String toString() {
        return getURL();
    }
}
