/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.product;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
    
    private static String INSERT_NEW_PRICE =  "INSERT INTO ProductPrice (productID, priceCode, price, createdAt)\n"
                                            + "VALUES (?, ?, ?, GETDATE());";
    private static String UPDATE_PRODUCT_INFO = "UPDATE Product\n"
                                         + "SET productName = ?, priceCode = ?, details = ?, productStatus = ?, typeID = ?\n"
                                         + "WHERE productID = ?";
    private static String DELETE_PRODUCT = "UPDATE Product SET productStatus = 4 WHERE productID = ?;";
    
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
        Product product = products.get(id);
        boolean productNotNull = Objects.nonNull(product);
        
        Optional<Product> productOptional = Optional.empty();
        if (productNotNull) {
            productOptional = Optional.of(product);
        }
        return productOptional;
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
    
    /**
     * Update Product Information for Given Product object
     * Order of update : SET productName = ?, priceCode = ?, details = ?, productStatus = ?, typeID = ?
     * @param product 
     */
    public void update(Product product)
    {
        if (product.isEmpty()) {
            System.out.println("Product Is NULL");
            return;
        }    
        
        int currentPriceVersion = getPriceVersion(product.getPriceCode());
        int newPriceCode = generatePriceCode(product.getProductID(), currentPriceVersion);
        try {
            //Update price when new Price is different with old price
            if(product.getUpdatePrice()) {
                //Create new price code for this product with order (productID, priceCode, price)
                openQuery(INSERT_NEW_PRICE); 
                query.setInt(1, product.getProductID());
                query.setInt(2, newPriceCode);
                query.setInt(3, product.getPrice());
                query.executeUpdate();
                closeQuery();
                product.setPriceCode(newPriceCode);
                System.out.println("Update price success, priceCode = " + newPriceCode);
            }
            
            //Update product info with data get from the Form
            openQuery(UPDATE_PRODUCT_INFO);
            
            query.setString(1, product.getProductName());
            query.setInt(2, product.getPriceCode());
            query.setString(3, product.getDetails());
            query.setInt(4, product.getProductStatus());
            query.setInt(5, product.getTypeID());
            query.setInt(6, product.getProductID());
            query.executeUpdate();
            
            System.out.println("UPDATE product info success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
    }
    
    /**
     * Return the current price version of this Product, extracted from its priceCode
     * @param priceCode
     * @return Current Price version extracted from priceCode
     */
    private static int getPriceVersion(int priceCode)
    {
        int result = 0;
        String extractedPriceCode = String.valueOf(priceCode).trim();
        Character seperator = '0';
        int seperatorPos = 0;

        boolean seperatorFound = false;
        for (int i = extractedPriceCode.length()-1; i > 0 && !seperatorFound; i--)
        {
            if(seperator.equals(extractedPriceCode.charAt(i)))
            {
                seperatorPos = i;
                seperatorFound = true;
            }
        }
        System.out.println("Old version: " + extractedPriceCode.substring(seperatorPos+1));
        result = Integer.parseInt(extractedPriceCode.substring(seperatorPos+1));
        return result;
    }
    
    public static int generatePriceCode(int productID, int currentVersion)
    {
        int newVersion = currentVersion + 1;
        
        StringBuffer buffer = new StringBuffer(String.valueOf(productID));
        buffer.append(0);
        buffer.append(newVersion);
        return Integer.parseInt(buffer.toString());
    }

    @Override
    public void delete(int id) {
        openQuery(DELETE_PRODUCT);
        
        try {
            query.setInt(1, id);
            query.executeUpdate();
            
            System.out.println("Delete product with id = " + id + " success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
    }

}
