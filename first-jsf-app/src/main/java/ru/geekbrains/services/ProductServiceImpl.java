package ru.geekbrains.services;


import ru.geekbrains.entities.Category;
import ru.geekbrains.entities.Product;
import ru.geekbrains.repositories.CategoryRepository;
import ru.geekbrains.repositories.ProductRepository;
import ru.geekbrains.rest.ProductServiceRest;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(ProductServiceRemote.class)
public class ProductServiceImpl implements ProductService, ProductServiceRemote, ProductServiceRest {
    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;


    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(this::builtProductDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);
        if(product != null) return builtProductDto(product);
        return null;
    }

    @Override
    public ProductDto findByName(String name) {
        Product product = productRepository.findByName(name);
        if(product != null) return builtProductDto(product);
        return null;
    }

    @Override
    public List<ProductDto> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
                .map(this::builtProductDto).collect(Collectors.toList());
    }

    private ProductDto builtProductDto(Product product){
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        Category category = product.getCategory();
        productDto.setCategoryId(category != null ? category.getId() : null);
        productDto.setCategoryName(category != null ? category.getName() : null);

        return productDto;
    }

    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @Override
    public void insert(ProductDto product) {
        if(product.getId() != null) throw new IllegalArgumentException();
        saveOrUpdate(product);
    }

    @Override
    public void update(ProductDto product) {
        if(product.getId() == null) throw new IllegalArgumentException();
        saveOrUpdate(product);
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(ProductDto product) {
        Category category = null;
        if (product.getCategoryId() != null) {
            category = categoryRepository.getReference(product.getCategoryId());
        }
        productRepository.saveOrUpdate(new Product(product, category));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
