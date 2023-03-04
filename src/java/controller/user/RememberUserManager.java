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
        //If remember User is not null, then assign it to optional
        if(rememberUser != null) customerOptional = Optional.of(rememberUser);
        
        return customerOptional;
    }
    
    public static void add(String hashCode, Customer rememberUser)
    {
        if(!rememberCustomers.containsKey(hashCode)) rememberCustomers.put(hashCode, rememberUser);
    }
    
    public static boolean remove(String hashCode)
    {
        boolean removeSuccess = false;
        
        if(rememberCustomers.containsKey(hashCode)) 
        {
            rememberCustomers.remove(hashCode);
            removeSuccess = true;
        }
        
        return removeSuccess;
    }
}
