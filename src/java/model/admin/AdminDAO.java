package model.admin;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import model.DAO.BaseDAO;

public class AdminDAO extends BaseDAO<Admin>{
    private static String LOGIN = "SELECT username, passwd FROM Admin WHERE username = ?";

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
    
    public Optional<Admin> Authenticate(String inputUserName, String inputPassword)
    {
        Optional<Admin> AdminOptional = Optional.empty();
        if(inputUserName == null || inputPassword == null) return AdminOptional;
        
        openQuery(LOGIN);
        try {
            query.setString(1, inputUserName);
            
            ResultSet resultSet = query.executeQuery();
            Admin admin = Admin.empty();
            
            while(resultSet.next())
            {
                String username = resultSet.getString("username");
                String password = resultSet.getString("passwd");
                
                admin = new Admin(username, password);
            }
            
            boolean isValid = admin.getPasswd().equals(inputPassword);
            
            //If valid, optional will include the customer
            if(isValid) AdminOptional = Optional.of(admin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
        return AdminOptional;
    }

    @Override
    public Optional<Admin> get(int id) {
        return Optional.empty();
    }

    @Override
    public Map<Integer, Admin> getAll() {
        return new HashMap<>();
    }

    @Override
    public void insert(Admin obj) {}

    @Override
    public void update(int id, String updateField, String updateValue) {}

    @Override
    public void update(int id, String... updateValue) {}

    @Override
    public void delete(int id) {}
}
