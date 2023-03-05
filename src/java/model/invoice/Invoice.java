package model.invoice;

import java.util.Date;
import model.customer.Customer;
import model.temporary_cart.TemporaryCart;


public class Invoice {
    //Database attribute section
    private String invoiceID;
    private int userID;
    private int totalMoney;
    private Date createdAt;
    
    //In app attribute section
    private TemporaryCart temporaryCart;
    private Customer user;
    
    /**
     * This constructor will used by servlet to create Invoice with already-initialized attributes
     * @param temporaryCart 
     */
    public Invoice(TemporaryCart temporaryCart)
    {
        this.user = user;
        this.temporaryCart = temporaryCart;
        initAttribute();
    }
    
    private void initAttribute()
    {
        
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
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

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }
}
