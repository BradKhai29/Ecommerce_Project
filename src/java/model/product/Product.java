package model.product;

import java.util.Optional;

/**
 *
 * @author This PC
 */
public class Product {    
    private static ProductTypeDAO productTypeDAO;
    
    //Database attributes section
    private int productID;
    private String productName;
    private String imgURL;
    private int price;
    private int priceCode;
    private String details;
    private int productStatus;
    private int typeID;
    private String typeName = "";
    
    //In app attribute section
    private int paymentQuantity;
    private int totalPrice;
    private String statusMessage = "";
    private boolean updatePrice = false;
    
    static {
       productTypeDAO = new ProductTypeDAO();
    }

    public Product() {
    }
    
    public static Product createNew()
    {
        return new Product();
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
        StringBuffer contextPath = new StringBuffer("product/");
        this.imgURL = contextPath.append(imgURL).toString();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        updatePrice = true;
        this.price = price;
    }
    
    public void confirmUpdatePrice(){
        updatePrice = false;
    }
    
    public boolean getUpdatePrice() 
    {
        boolean result = updatePrice;
        confirmUpdatePrice();
        return result;
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

    public String getStatusMessage() {
        if(statusMessage.equals("")) 
        {
            statusMessage = ProductStatusEnum.getStatusMessage(productStatus);
        }
        return statusMessage;
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
        boolean emptyTypeName = typeName.equals("");
        
        if(emptyTypeName) {
            Optional<ProductType> typeOptional = Optional.empty();
            typeOptional = productTypeDAO.get(typeID);
            typeName = typeOptional.get().getTypeName();
        }
        return typeName;
    }

    public int getPaymentQuantity() {
        return paymentQuantity;
    }

    public void setPaymentQuantity(int paymentQuantity) {
        this.paymentQuantity = paymentQuantity;
    }

    public int getTotalPrice() {
        totalPrice = paymentQuantity * price;
        return totalPrice;
    }
    
    @Override
    public String toString() {
        return ("product" + productName + ":" + productID + ":" + price + ":" + priceCode + ":" + imgURL);
    }
    
    public boolean isEmpty()
    {
        return productName == null || imgURL == null;
    }
}
