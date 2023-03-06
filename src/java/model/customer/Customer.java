package model.customer;

import java.util.Map;
import model.invoice.Invoice;
import model.invoice.InvoiceDAO;

public class Customer {
    int userID;
    String username;
    String email;
    String passwd;
    
    String fullname;
    String phoneNumber;
    String userAddress = "";
    
    //bỏ thêm thằng transaction map ở đây để hỗ trợ tính năng in lịch sử
    private InvoiceDAO invoiceDAO = new InvoiceDAO();
    private Map<Integer, Invoice> invoices;

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
        this.userAddress = userAddress;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public Map<Integer, Invoice> getInvoices() {
        if(invoices == null) setInvoices(invoiceDAO.getAll(userID));
        return invoices;
    }

    public void setInvoices(Map<Integer, Invoice> invoices) {
        this.invoices = invoices;
    }
}
