package controller.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import model.customer.Customer;

public final class RememberUserManager {
    private static final Map<String, Customer> rememberCustomers;
    
    static {
        rememberCustomers = new HashMap<>();
    }
    
    public static Optional<Customer> get(String hashCode)
    {
        //Init as empty optional
        Optional<Customer> customerOptional = Optional.empty();
        Customer rememberUser = rememberCustomers.get(hashCode);
        
        boolean isExist = rememberUser != null;
        if(isExist) {
            System.out.println("Get User from key : ["  + hashCode + "]");
            customerOptional = Optional.of(rememberUser);
        }
        
        return customerOptional;
    }
    
    public static void add(String hashCode, Customer rememberUser)
    {
        if(!rememberCustomers.containsKey(hashCode)) 
        {
            System.out.println("Add User with key : ["  + hashCode + "]");
            rememberCustomers.put(hashCode, rememberUser);
        }
    }
    
    public static boolean remove(String hashCode)
    {
        boolean removeSuccess = false;
        
        if(rememberCustomers.containsKey(hashCode)) 
        {
            rememberCustomers.remove(hashCode);
            removeSuccess = true;
            System.out.println("Remove User with key : ["  + hashCode + "]");
        }
        
        return removeSuccess;
    }
}
