/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.invoice;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import model.DAO.BaseDAO;
import model.temporary_cart.TemporaryCart;
import model.product.*;

/**
 *
 * @author This PC
 */
public class InvoiceDAO extends BaseDAO<Invoice> {
    private static ProductDAO productDAO = new ProductDAO();
    private Map<Integer, Invoice> invoices = new HashMap<>();

    private static String INSERT_INVOICE = "INSERT INTO Invoice (invoiceID, userID, totalMoney, createdAt) VALUES (?, ?, ?, ?);";
    private static String INSERT_INVOICE_DETAIL = "INSERT INTO InvoiceDetail (invoiceID, productID, priceCode, quantity) VALUES (?, ?, ?, ?);";
    
    private static String GET_INVOICE_WITH_USER_ID = "SELECT invoiceID, totalMoney, createdAt\n"
                                                   + "FROM Invoice WHERE userID = ?;";
    
    private static String GET_INVOICE_DETAIL =  "SELECT id.productID, id.priceCode, quantity\n" +
                                                "FROM Invoice i \n" +
                                                "INNER JOIN InvoiceDetail id ON i.invoiceID = id.invoiceID\n" +
                                                "WHERE id.invoiceID = ?";

    @Override
    protected void openQuery(String SQLQuery) {
        openConnection();
        try {
            query = DBConnection.prepareStatement(SQLQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void closeQuery() {
        try {
            query.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public Optional<Invoice> get(int invoiceID) {
        Optional invoiceOptional = Optional.empty();

        Invoice invoice = invoices.get(invoiceID);
        if (invoice != null) {
            invoiceOptional = Optional.of(invoice);
        }

        return invoiceOptional;
    }

    public Map<Integer, Invoice> getAll(int userID) {
        System.out.println("Get load Invoice from invoice DAO");
        try {
            getAllInvoiceWithUserID(userID);
            getProductBelongToInvoice();
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeQuery();
        return invoices;
    }

    private void getAllInvoiceWithUserID(int userID) throws SQLException {
        openQuery(GET_INVOICE_WITH_USER_ID);
        System.out.println("userid = [" + userID + "]");
        query.setInt(1, userID);
        ResultSet resultSet = query.executeQuery();

        //Get list of invoice first
        while (resultSet.next()) 
        {
            //Create temporary cart to store product
            TemporaryCart temporaryCart = TemporaryCart.createNew();
            
            int invoiceID = resultSet.getInt("invoiceID");
            int totalMoney = resultSet.getInt("totalMoney");
            Date createAt = resultSet.getDate("createdAt");
            Invoice invoice = new Invoice(invoiceID, userID, totalMoney, createAt, temporaryCart);
            
            if (!invoices.containsKey(invoiceID)) {
                //UnComment below line to debug
                //System.out.println("putting invoice id [" + invoiceID + "]");
                invoices.put(invoiceID, invoice);
            }
        }
        
        closeQuery();
    }
    
    private void getProductBelongToInvoice() throws SQLException
    {        
        for (Map.Entry<Integer, Invoice> invoiceEntry : invoices.entrySet())
        {
            //Open query for each time loading the product
            openQuery(GET_INVOICE_DETAIL);
            
            int invoiceID = invoiceEntry.getKey();
            Invoice invoice = invoiceEntry.getValue();
            TemporaryCart invoiceTemporaryCart = invoice.getTemporaryCart();
            
            query.setInt(1, invoiceID);
            ResultSet resultSet = query.executeQuery();
            
            //Get product list belong to this invoice
            while(resultSet.next())
            {
                int productID = resultSet.getInt("productID");
                int priceCode = resultSet.getInt("priceCode");
                int quantity = resultSet.getInt("quantity");
                
                Product product = productDAO.get(productID, priceCode);
                invoiceTemporaryCart.add(product, quantity);
            }
        }
        
        closeQuery();
    }

    @Override
    public Map<Integer, Invoice> getAll() {
        return invoices;
    }

    @Override
    public void insert(Invoice invoice) {
        try {
            insertToMap(invoice);
            insertInvoice(invoice);
            insertInvoiceDetail(invoice);
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeQuery();
    }

    private void insertToMap(Invoice invoice) {
        if (!invoices.containsKey(invoice.getInvoiceID())) {
            invoices.put(invoice.getInvoiceID(), invoice);
        }
    }

    private void insertInvoice(Invoice invoice) throws SQLException {
        //Get information
        openQuery(INSERT_INVOICE);

        int invoiceID = invoice.getInvoiceID();
        int userID = invoice.getUserID();
        int totalMoney = invoice.getTotalMoney();

        query.setInt(1, invoiceID);
        query.setInt(2, userID);
        query.setInt(3, totalMoney);
        query.setDate(4, invoice.getCreatedAt());
        query.execute();

        closeQuery();
    }

    private void insertInvoiceDetail(Invoice invoice) throws SQLException {
        openQuery(INSERT_INVOICE_DETAIL);

        int invoiceID = invoice.getInvoiceID();
        TemporaryCart temporaryCart = invoice.getTemporaryCart();
        Map<Integer, Product> productCart = temporaryCart.getProductCart();

        for (Map.Entry<Integer, Product> productEntry : productCart.entrySet()) {
            //Get information
            int productID = productEntry.getKey();
            Product product = productEntry.getValue();
            int priceCode = product.getPriceCode();
            int quantity = product.getPaymentQuantity();

            //Prepare for execute
            query.setInt(1, invoiceID);
            query.setInt(2, productID);
            query.setInt(3, priceCode);
            query.setInt(4, quantity);
            query.execute();
        }

        closeQuery();
    }

    @Override
    public void update(int id, String updateField, String updateValue) {
    }

    @Override
    public void update(int id, String... updateValue) {
    }

    @Override
    public void delete(int id) {
    }
    
    public void clear()
    {
        invoices.clear();
    }

}
