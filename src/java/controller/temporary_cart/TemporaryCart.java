package controller.temporary_cart;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import model.customer.Customer;
import model.product.Product;

public class TemporaryCart {
    int size;
    private Optional<Customer> user;
    private Map<Integer, Product> productCart = productCart = new HashMap<>();

    public TemporaryCart() {
        user = Optional.empty();
    }

    public TemporaryCart(Customer user) {
        if(user == null) this.user = Optional.empty();
        else this.user = Optional.of(user);
    }

    public Optional<Customer> getUser() {
        return user;
    }

    public Map<Integer, Product> getProductCart() {
        return productCart;
    }

    public int getSize() {
        return productCart.size();
    }
    
    public void add(Product product, int quantity)
    {
        int productID = product.getProductID();
        product.setPaymentQuantity(quantity);
        
        if(!productCart.containsKey(productID)) 
        {
            productCart.put(productID, product);
        }
    }
    
    public void updateQuantity(int productID, int newQuantity)
    {
        Product product = productCart.get(productID);
        if(product != null) product.setPaymentQuantity(newQuantity);
    }
    
    public void remove(int productID)
    {
        productCart.remove(productID);
    }
    
    public void clear()
    {
        productCart.clear();
    }
}
