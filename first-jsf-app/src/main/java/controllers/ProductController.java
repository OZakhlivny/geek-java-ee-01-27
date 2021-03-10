package controllers;

import dto.CategoryDto;
import dto.ProductDto;
import entities.Product;
import services.CategoryService;
import services.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@SessionScoped
public class ProductController implements Serializable {
    @EJB
    private ProductService productService;

    @EJB
    private CategoryService categoryService;

    private ProductDto product;
    private List<ProductDto> products;
    private List<CategoryDto> categories;


    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new ProductDto();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        categories = categoryService.findAll();
        products = productService.findAll();
    }

    public List<ProductDto> getAllProducts() {
        return products;
    }

    public String editProduct(ProductDto product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product) {
        productService.deleteById(product.getId());
    }

    public String saveProduct() {
        productService.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect=true";
    }

    public List<CategoryDto> getAllCategories(){
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }
}
