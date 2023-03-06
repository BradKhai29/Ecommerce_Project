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
public class ProductDAO extends model.DAO.BaseDAO<Product> {

    private static Map<Integer, Product> products;

    static {
        products = new HashMap<>();
    }

    private static String SELECT_ALL = "SELECT p.productID, productName, price, p.priceCode, imgURL, details, productStatus, typeID "
            + "FROM Product p INNER JOIN ProductPrice pp ON p.priceCode = pp.priceCode";

    private static String SELECT_WITH_PRODUCTID_AND_PRICECODE = "SELECT productName, price, imgURL, typeID, productStatus \n"
            + "FROM Product p INNER JOIN ProductPrice pp ON p.priceCode = pp.priceCode\n"
            + "WHERE p.priceCode = ?";

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
    public Optional<Product> get(int id) {
        Optional<Product> product = Optional.of(products.get(id));
        return product;
    }

    /**
     * Get product with given productID and priceCode
     *
     * @param productID
     * @param priceCode
     * @return <span style="color:red">Product obj</span> with given ProductID and PriceCode
     */
    public Product get(int productID, int priceCode) {
        System.out.println("Getting product with given [" + productID + "], priceCode [" + priceCode + "]");
        
        //Create new product instances
        Product product = Product.createNew();
        product.setProductID(productID);
        openQuery(SELECT_WITH_PRODUCTID_AND_PRICECODE);

        try {
            query.setInt(1, priceCode);

            ResultSet resultSet = query.executeQuery();
            
            while (resultSet.next()) 
            {
                String productName = resultSet.getString("productName");
                int price = resultSet.getInt("price");
                String imgURL = resultSet.getString("imgURL");
                int typeID = resultSet.getInt("typeID");
                int productStatus = resultSet.getInt("productStatus");

                product.setProductName(productName);
                product.setPrice(price);
                product.setImgURL(imgURL);
                product.setTypeID(typeID);
                product.setProductStatus(productStatus);

                webpage_tools.PrintTools.print(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeQuery();
        return product;
    }

    @Override
    public Map<Integer, Product> getAll() {
        openQuery(SELECT_ALL);

        try {
            ResultSet resultSet = query.executeQuery();

            while (resultSet.next()) {
                int productID = resultSet.getInt("productID");
                String productName = resultSet.getString("productName");
                int price = resultSet.getInt("price");
                int priceCode = resultSet.getInt("priceCode");
                String imgURL = resultSet.getString("imgURL");
                String details = resultSet.getString("details");
                int productStatus = resultSet.getInt("productStatus");
                int typeID = resultSet.getInt("typeID");

                Product product = new Product(productID, productName, imgURL, price, priceCode, details, productStatus, typeID);
                //System.out.println(product);

                products.put(productID, product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeQuery();
        return products;
    }

    /**
     * Load the Product List with reload option
     *
     * @param reload true : reload again from DB, false: not reload
     * @return
     */
    public Map<Integer, Product> getAll(boolean reload) {
        if (reload) {
            products.clear();
            getAll();
            return products;
        }
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
