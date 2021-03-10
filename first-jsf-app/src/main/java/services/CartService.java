package services;


import dto.ProductDto;

import javax.ejb.Local;
import java.util.Map;

@Local
public interface CartService {

    Map<ProductDto, Integer> getAllProducts();

    void addToCart(ProductDto product);

    void decrementProductAmount(ProductDto product);

    void removeFromCart(ProductDto product);

    boolean isEmptyCart();
}
