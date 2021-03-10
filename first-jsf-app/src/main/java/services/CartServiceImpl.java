package services;

import dto.ProductDto;

import javax.ejb.Stateful;
import java.util.HashMap;
import java.util.Map;

@Stateful
public class CartServiceImpl implements CartService{

    private final Map<ProductDto, Integer> productMap = new HashMap<>();

    public Map<ProductDto, Integer> getAllProducts() {
        return productMap;
    }

    @Override
    public void addToCart(ProductDto product) {
        if(productMap.get(product) == null){
            productMap.put(product, 1);
        }
        else{
            productMap.put(product, productMap.get(product) + 1);
        }
    }

    @Override
    public void decrementProductAmount(ProductDto product) {
        Integer amount = productMap.get(product);

        if(amount != null){
            if(amount > 1) productMap.put(product, amount - 1);
            else removeFromCart(product);
        }
    }

    @Override
    public void removeFromCart(ProductDto product) {
        productMap.remove(product);
    }

    @Override
    public boolean isEmptyCart() {
        return productMap.isEmpty();
    }
}
