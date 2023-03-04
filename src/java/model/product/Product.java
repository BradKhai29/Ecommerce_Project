/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.product;

import java.util.Objects;

/**
 *
 * @author This PC
 */
public class Product {    
    private static ProductTypeDAO productTypeDAO;
    
    int productID;
    String productName;
    String imgURL;
    int price;
    int priceCode;
    String details;
    int productStatus;
    int typeID;
    String typeName = "";
    
    int paymentQuantity;
    
    static {
       productTypeDAO = new ProductTypeDAO();
    }

    public Product() {
    }

    public Product(int productID, String productName, String imgURL, int price, int priceCode, String details, int productStatus, int typeID) {
        StringBuffer contextPath = new StringBuffer("product/");  
        
        this.productID = productID;
        this.productName = productName;
        this.imgURL = contextPath.append(imgURL).toString();
        this.price = price;
        this.priceCode = priceCode;
        this.details = details;
        this.productStatus = productStatus;
        this.typeID = typeID;
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

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int priceCode) {
        this.priceCode = priceCode;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public int getTypeID() {
        return typeID;
    }

    public String getTypeName() {
        if(typeName.equals("")) typeName = productTypeDAO.get(this.typeID).get().getTypeName();
        return typeName;
    }

    @Override
    public String toString() {
        return ("product" + productName + ":" + productID + ":" + price + ":" + priceCode + ":" + details);
    }

    public int getPaymentQuantity() {
        return paymentQuantity;
    }

    public void setPaymentQuantity(int paymentQuantity) {
        this.paymentQuantity = paymentQuantity;
    }
}
