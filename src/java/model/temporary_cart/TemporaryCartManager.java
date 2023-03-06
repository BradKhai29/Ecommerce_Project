package model.temporary_cart;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class TemporaryCartManager {
    private static final Map<String, TemporaryCart> temporaryCarts;
    
    static {
        temporaryCarts = new HashMap<>();
    }
    
    public static void add(String key, TemporaryCart temporaryCart)
    {
        if(!temporaryCarts.containsKey(key)) 
        {
            System.out.println("Add tempcart with key [" + key + "]");
            temporaryCarts.put(key, temporaryCart);
        }
    }
    
    /**
     * Remove Temporary Cart belong to given key
     * @param key 
     */
    public static void remove(String key)
    {
        System.out.println("Remove tempcart with key [" + key + "]");
        temporaryCarts.remove(key);
    }
    
    /**
     * Remove empty temporary cart with key = username
     * @param username
     * @param checkEmpty 
     */
    public static void remove(String username, boolean checkEmpty)
    {
        //System.out.println("Get to Remove with CheckEmpty");
        TemporaryCart temporaryCart = temporaryCarts.get(username);
        if(temporaryCart != null)
        {
            //System.out.println("TemporaryCart found!");
            if(temporaryCart.getSize() < 1 && checkEmpty) 
            {
                //System.out.println("Do Remove Empty Cart with key = [" + username + "]");
                temporaryCarts.remove(username);
            }
        }
    }
    
    public static Optional<TemporaryCart> get(String key)
    {
        //init optional as empty
        Optional<TemporaryCart> temporaryCart = Optional.empty();
        
        //Get the tempCart and check if it is existed or not
        TemporaryCart tempCart = temporaryCarts.get(key);
        if(tempCart != null) {
            //System.out.println("Get tempcart with key [" + key + "]");
            temporaryCart = Optional.of(tempCart);
        }
        
        return temporaryCart;
    }
}
