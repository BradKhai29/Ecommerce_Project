package model.temporary_cart;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import model.customer.Customer;
import model.product.Product;

public class TemporaryCart {
    private int size;
    private int totalPrice = 0;
    private boolean doUpdate = false;
    
    private Optional<Customer> user;
    private Map<Integer, Product> productCart = new HashMap<>();

    public TemporaryCart() {
        user = Optional.empty();
    }

    public TemporaryCart(Customer user) 
    {
        if(user == null) this.user = Optional.empty();
        else this.user = Optional.of(user);
    }
    
    public static TemporaryCart createNew()
    {
        return new TemporaryCart();
    }

    public Optional<Customer> getUser() {
        return user;
    }

    public void setUser(Optional<Customer> user) {
        this.user = user;
    }
    
    public int getUserID()
    {
        if(user == null) 
        {
            System.out.println("THIS TEMPORARY CART DID NOT SET THE USER");
            return 0;
        }
        return user.get().getUserID();
    }

    public Map<Integer, Product> getProductCart() {
        return productCart;
    }

    public int getSize() {
        return productCart.size();
    }

    public int getTotalPrice() 
    {
        int price = 0;
        
        //if do some update to cart, then update again the total price
        if(doUpdate)
        {
            totalPrice = 0;
            for (Map.Entry<Integer, Product> productEntry : productCart.entrySet()) 
            {
                Product product = productEntry.getValue();
                price = product.getTotalPrice();
                totalPrice += price;
            }
            doUpdate = false;
        }
        return totalPrice;
    }
    
    public void add(Product product, int quantity)
    {
        int productID = product.getProductID();
        product.setPaymentQuantity(quantity);
        
        if(!productCart.containsKey(productID))
        {
            productCart.put(productID, product);
            doUpdate = true;
        }
    }
    
    public void updateQuantity(int productID, int newQuantity)
    {
        Product product = productCart.get(productID);
        if(product != null) 
        {
            product.setPaymentQuantity(newQuantity);
            doUpdate = true;
        }
    }
    
    public void remove(int productID)
    {
        productCart.remove(productID);
        doUpdate = true;
    }
    
    public void clear()
    {
        productCart.clear();
        doUpdate = true;
    }
}
