package model.product;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author This PC
 */
public class ProductTypeDAO extends model.DAO.BaseDAO<ProductType>{
    private static String SELECT_ALL = "SELECT typeID, typeName FROM ProductType";
    private static Map<Integer, ProductType> productTypes = new HashMap<>();
    
    @Override
    protected void openQuery(String SQLQuery) {
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
    }

    @Override
    public Optional<ProductType> get(int id) {
        if(productTypes.size() < 1) getAll();
        return Optional.of(productTypes.get(id));
    }

    @Override
    public Map<Integer, ProductType> getAll() {
        if(productTypes.size() > 1) return productTypes;
        openConnection();
        openQuery(SELECT_ALL);
        
        try {
            ResultSet resultset = query.executeQuery();
            
            while(resultset.next())
            {
                int typeID = resultset.getInt("typeID");
                String typeName = resultset.getString("typeName");
                ProductType productType = new ProductType(typeID, typeName);
                productTypes.put(typeID, productType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productTypes;
    }

    @Override
    public void insert(ProductType obj) {}

    @Override
    public void update(int id, String updateField, String updateValue) {}

    @Override
    public void update(int id, String... updateValue) {}

    @Override
    public void delete(int id) {}
    
}
