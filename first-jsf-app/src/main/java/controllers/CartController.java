package controllers;
import entities.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@Named
@SessionScoped
public class CartController implements Serializable {

    private Map<Product, Integer> productMap = new HashMap<>();

    public Map<Product, Integer> getAllProducts() {
        return productMap;
    }

    public String addToCart(Product product) {
        if(productMap.get(product) == null){
            productMap.put(product, 1);
        }
        else{
            productMap.put(product, productMap.get(product) + 1);
        }
        return "";
    }

    public String decrementProductAmount(Product product){
        Integer amount = productMap.get(product);

        if(amount != null){
            if(amount > 1) productMap.put(product, amount - 1);
            else removeFromCart(product);
        }
        return "";
    }

    public String removeFromCart(Product product) {
        productMap.remove(product);
        return "";
    }

    public boolean isEmptyCart(){
        return productMap.isEmpty();
    }
}
