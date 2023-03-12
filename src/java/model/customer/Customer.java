package model.customer;

import java.util.Map;
import java.util.Objects;
import model.invoice.Invoice;
import model.invoice.InvoiceDAO;

public class Customer {
    //Database Attributes section
    int userID;
    String username;
    String email;
    String passwd;
    String fullname;
    String phoneNumber;
    String userAddress = "";
    
    //In app section
    private InvoiceDAO invoiceDAO = new InvoiceDAO();
    private Map<Integer, Invoice> invoices;
    private long totalPayAmount;
    boolean makePayment = true;

    public static Customer empty()
    {
        return new Customer(0, "", "", "", "", "");
    }
    
    public Customer() {
    }

    public Customer(int userID, String username, String email, String passwd, String fullname, String phoneNumber) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.passwd = passwd;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
    }
    
    public Customer(int userID, String username, String email, String passwd, String fullname, String phoneNumber, String userAddress) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.passwd = passwd;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        if(userAddress != null) this.userAddress = userAddress;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.length() < 1) {
            return;
        }
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        boolean isValid = Objects.nonNull(userAddress) && !String.valueOf(userAddress).trim().isEmpty();
        
        if(isValid) this.userAddress = userAddress;
    }

    public Map<Integer, Invoice> getInvoices() {
        System.out.println("Get invoice list from Database with Username = [" + username + "]");
        invoices = invoiceDAO.getAll(userID);
        return invoices;
    }

    public void setInvoices(Map<Integer, Invoice> invoices) {
        this.invoices = invoices;
    }

    public long getTotalPayAmount() {
        int invoiceMoney = 0;
        if(invoices == null) invoices = invoiceDAO.getAll(userID);
        
        for (Map.Entry<Integer, Invoice> invoiceEntry : invoices.entrySet()) {
            Invoice invoice = invoiceEntry.getValue();
            invoiceMoney += invoice.getTotalMoney();
        }
        
        totalPayAmount = invoiceMoney;
        return totalPayAmount;
    }
    
    public void MakeNewPayment()
    {
        makePayment = true;
    }
    
    public boolean HaveNewPayment()
    {
        boolean result = makePayment;
        makePayment = false;
        
        return result;
    }
    
    public void updateProfile(String fullname, String email, String phoneNumber, String userAddress)
    {
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userAddress = userAddress;
    }
}
