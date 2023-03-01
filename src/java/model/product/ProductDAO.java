/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.product;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author This PC
 */
public class ProductDAO extends model.DAO.BaseDAO<Product>{
    private static String SELECT_ALL = "SELECT productID, productName, price, p.priceCode, imgURL, details, available "
                                     + "FROM Product p INNER JOIN ProductPrice pp ON p.priceCode = pp.priceCode";
    
    private static String SELECT =  "SELECT productName, price, p.priceCode, imgURL, details, available \n" +
                                    "FROM Product p INNER JOIN ProductPrice pp ON p.priceCode = pp.priceCode\n" +
                                    "WHERE productID = ?";
    
    @Override
    protected void openQuery(String SQLQuery) {
        
    }

    @Override
    protected void closeQuery() {
    
    }

    @Override
    public Optional<Product> get(int id) {
        Optional<Product> product = Optional.empty();
        return product;
    }

    @Override
    public Map<Integer, Product> getAll() {
        Map<Integer, Product> products = new HashMap<>();
        openQuery(SELECT_ALL);
        
        try {
            ResultSet resultSet = query.executeQuery();
            
            while(resultSet.next())
            {
                int productID = resultSet.getInt("productID");
                String productName = resultSet.getString("productName");
                int price = resultSet.getInt("price");
                int priceCode = resultSet.getInt("p.priceCode");
                String imgURL = resultSet.getString("imgURL");
                String details = resultSet.getString("details");
                boolean available = resultSet.getBoolean("available");
                
                Product product = new Product(productID, productName, imgURL, price, priceCode, details, available);
                products.put(productID, product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
        closeConnection();
        return products;
    }

    @Override
    public void insert(Product obj) {
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
    
}
