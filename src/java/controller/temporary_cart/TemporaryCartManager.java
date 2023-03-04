package controller.temporary_cart;

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
    
    public static void remove(String key)
    {
        System.out.println("Remove tempcart with key [" + key + "]");
        temporaryCarts.remove(key);
    }
    
    public static Optional<TemporaryCart> get(String key)
    {
        //init optional as empty
        Optional<TemporaryCart> temporaryCart = Optional.empty();
        
        //Get the tempCart and check if it is existed or not
        TemporaryCart tempCart = temporaryCarts.get(key);
        if(tempCart != null) {
            System.out.println("Get tempcart with key [" + key + "]");
            temporaryCart = Optional.of(tempCart);
        }
        
        return temporaryCart;
    }
}
