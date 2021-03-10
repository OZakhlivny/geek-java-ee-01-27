package controllers;
import dto.ProductDto;
import services.CartService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;


@Named
@SessionScoped
public class CartController implements Serializable {

    @EJB
    CartService cartService;

    public Map<ProductDto, Integer> getAllProducts() {
        return cartService.getAllProducts();
    }

    public String addToCart(ProductDto product) {
        cartService.addToCart(product);
        return "";
    }

    public String decrementProductAmount(ProductDto product){
        cartService.decrementProductAmount(product);
        return "";
    }

    public String removeFromCart(ProductDto product) {
        cartService.removeFromCart(product);
        return "";
    }

    public boolean isEmptyCart(){
        return cartService.isEmptyCart();
    }
}
