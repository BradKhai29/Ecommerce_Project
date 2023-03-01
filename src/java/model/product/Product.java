/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.product;

/**
 *
 * @author This PC
 */
public class Product {
    private final static String contextPath = "product/";
    
    int productID;
    String productName;
    String imgURL;
    int price;
    String details;
    boolean available;

    public Product() {
    }

    public Product(int productID, String productName, String imgURL, int price, String details, boolean available) {
        this.productID = productID;
        this.productName = productName;
        this.imgURL = imgURL;
        this.price = price;
        this.details = details;
        this.available = available;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
