package controllers;

import entities.Category;
import entities.Product;
import repositories.CategoryRepository;
import repositories.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {
    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    private Product product;
    private List<Product> products;
    private List<Category> categories;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new Product();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        categories = categoryRepository.findAll();
        products = productRepository.findAll();
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public String editProduct(Product product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product) {
        productRepository.deleteById(product.getId());
    }

    public String saveProduct() {
        productRepository.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect=true";
    }

    public List<Category> getAllCategories(){
        return categories;
    }
}
