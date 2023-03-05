package model.invoice;

import java.sql.Date;
import model.temporary_cart.TemporaryCart;


public class Invoice {
    //Database attribute section
    private int invoiceID;
    private int userID;
    private int totalMoney;
    private Date createdAt;
    
    //In app attribute section
    private TemporaryCart temporaryCart;
    
    /**
     * This constructor will used by servlet to create Invoice with already-initialized attributes
     * @param temporaryCart 
     */
    public Invoice(TemporaryCart temporaryCart)
    {
        this.temporaryCart = temporaryCart;
        initAllAttributes();
    }

    /**
     * This Constructor will be used by InvoiceDAO to load data from Database
     * @param invoiceID
     * @param userID
     * @param totalMoney
     * @param createdAt
     * @param temporaryCart 
     */
    public Invoice(int invoiceID, int userID, int totalMoney, Date createdAt, TemporaryCart temporaryCart) {
        this.invoiceID = invoiceID;
        this.userID = userID;
        this.totalMoney = totalMoney;
        this.createdAt = createdAt;
        this.temporaryCart = temporaryCart;
    }
    
    private void initAllAttributes()
    {
        invoiceID = hashCode();
        userID = temporaryCart.getUserID();
        totalMoney = temporaryCart.getTotalPrice();
        
        java.util.Date currentDate = new java.util.Date();
        createdAt = new Date(currentDate.getTime());
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public TemporaryCart getTemporaryCart() {
        return temporaryCart;
    }

    public void setTemporaryCart(TemporaryCart temporaryCart) {
        this.temporaryCart = temporaryCart;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}
