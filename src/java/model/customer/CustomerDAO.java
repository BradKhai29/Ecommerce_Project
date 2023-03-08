package model.customer;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import model.DAO.BaseDAO;

/**
 *
 * @author This PC
 */
public class CustomerDAO extends BaseDAO<Customer> {
    private static Map<Integer, Customer> customers;
    
    static {
        customers = new HashMap<>();
    }
    
    private static String INSERT = "INSERT INTO Customer (fullname, phoneNumber, userAddress, email, username, passwd) VALUES (?, ?, ?, ?, ?, ?)";
    private static String SELECT_USER = "SELECT * FROM Customer WHERE userID = ?";
    private static String SELECT_USER_WITH_USERNAME = "SELECT * FROM Customer WHERE username = ?";
    private static String CHECK_EXIST_USERNAME = "SELECT username FROM Customer WHERE username = ?";
    private static String UPDATE_PROFILE =  "UPDATE Customer \n" +
                                            "SET fullName = ?, email = ?, phoneNumber = ?, userAddress = ? \n" +
                                            "WHERE userID = ?;";
    private static String UPDATE_PASSWORD = "UPDATE Customer \n" +
                                            "SET passwd = ? \n" +
                                            "WHERE userID = ?;";

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
    public Optional<Customer> get(int id) {
        if(customers.containsKey(id)) {
            return Optional.of(customers.get(id));
        }
        
        openQuery(SELECT_USER);
        Optional<Customer> customerOptional = Optional.empty();
        try {
            query.setInt(1, id);
            
            ResultSet resultSet = query.executeQuery();
            Customer customer = Customer.empty();
            
            while(resultSet.next())
            {
                int userID = resultSet.getInt("userID");
                String username = resultSet.getString("username");
                String passwd = resultSet.getString("passwd");
                String fullname = resultSet.getString("fullname");
                String userAddress = resultSet.getString("userAddress");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                
                customer = new Customer(userID, username, email, passwd, fullname, phoneNumber, userAddress);
            }
            
            if(!customers.containsKey(id)) customers.put(id, customer);
            customerOptional = Optional.of(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
        return customerOptional;
    }

    @Override
    public Map<Integer, Customer> getAll() {
        return customers;
    }

    @Override
    public void insert(Customer customer) {
        openQuery(INSERT);
        
        try {
            query.setString(1, customer.getFullname());
            query.setString(2, customer.getPhoneNumber());
            query.setString(3, customer.getUserAddress());
            query.setString(4, customer.getEmail());
            query.setString(5, customer.getUsername());
            query.setString(6, customer.getPasswd());
            query.executeUpdate();
            
            System.out.println("REGISTER USER SUCCESS");
        } catch (Exception e) {
            System.out.println("ERROR FOUND WHILE REGISTER USER");
            e.printStackTrace();
        }
        
        closeQuery();
    }

    @Override
    public void update(int id, String updateField, String updateValue) {
    }

    @Override
    public void update(int id, String... updateValue) {
    }
    
    public void updateProfile(Customer user, String fullname, String email, String phoneNumber, String userAddress) 
    {
        openQuery(UPDATE_PROFILE);
        int id = user.getUserID();
        
        try {
            query.setString(1, fullname);
            query.setString(2, email);
            query.setString(3, phoneNumber);
            query.setString(4, userAddress);
            query.setInt(5, id);
            
            query.executeUpdate();
            System.out.println("Update user [" + id + "] success");
            user.updateProfile(fullname, email, phoneNumber, userAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
    }
    
    public void updatePassword(Customer user, String password)
    {
        openQuery(UPDATE_PASSWORD);
        int id = user.getUserID();
        
        try {
            query.setString(1, password);
            query.setInt(2, id);
            
            query.executeUpdate();
            System.out.println("Update user [" + id + "] success");
            user.setPasswd(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
    }

    @Override
    public void delete(int id) {
    }
    
    public Optional<Customer> Authenticate(String inputUserName, String inputPassword)
    {
        //Declare userOptional as empty optional
        Optional<Customer> userOptional = Optional.empty();
        if(inputUserName == null || inputPassword == null) return userOptional;
        
        openQuery(SELECT_USER_WITH_USERNAME);
        
        try {
            query.setString(1, inputUserName);
            
            ResultSet resultSet = query.executeQuery();
            Customer customer = Customer.empty();
            
            while(resultSet.next())
            {
                int userID = resultSet.getInt("userID");
                String username = resultSet.getString("username");
                String passwd = resultSet.getString("passwd");
                String fullname = resultSet.getString("fullname");
                String userAddress = resultSet.getString("userAddress");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                
                customer = new Customer(userID, username, email, passwd, fullname, phoneNumber, userAddress);
            }
            
            boolean isValid = customer.getPasswd().equals(inputPassword);
            
            //If valid, optional will include the customer
            if(isValid) userOptional = Optional.of(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
        return userOptional;
    }
    
    public boolean checkExistUsername(String username)
    {
        boolean isExist = false;
        openQuery(CHECK_EXIST_USERNAME);
        
        try {
            query.setString(1, username);
            
            ResultSet resultSet = query.executeQuery();
            while(resultSet.next())
            {
                isExist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
        return isExist;
    }
}
