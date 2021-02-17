package ru.geekbrains.listeners;

import ru.geekbrains.entities.Category;
import ru.geekbrains.entities.Product;
import ru.geekbrains.entities.User;
import ru.geekbrains.repositories.CategoryRepository;
import ru.geekbrains.repositories.ProductRepository;
import ru.geekbrains.repositories.UserRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        fillProductRepository(sce);
        fillCategoryRepository(sce);
        fillUserRepository(sce);
    }

    private void fillProductRepository(ServletContextEvent sce){
        ProductRepository productRepository = new ProductRepository();
        productRepository.saveOrUpdate(new Product(null, "Product  1",
                "Description of product 1", new BigDecimal(100)));
        productRepository.saveOrUpdate(new Product(null, "Product  2",
                "Description of product 2", new BigDecimal(200)));
        productRepository.saveOrUpdate(new Product(null, "Product  3",
                "Description of product 3", new BigDecimal(200)));

        sce.getServletContext().setAttribute("productRepository", productRepository);
    }

    private void fillCategoryRepository(ServletContextEvent sce){
        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.saveOrUpdate(new Category(null, "Category 1", "Description of category 1"));
        categoryRepository.saveOrUpdate(new Category(null, "Category 2", "Description of category 2"));
        categoryRepository.saveOrUpdate(new Category(null, "Category 3", "Description of category 3"));

        sce.getServletContext().setAttribute("categoryRepository", categoryRepository);
    }

    private void fillUserRepository(ServletContextEvent sce){
        UserRepository userRepository = new UserRepository();
        userRepository.saveOrUpdate(new User(null, "User 1", "user1@gmail.com"));
        userRepository.saveOrUpdate(new User(null, "User 2", "user2@gmail.com"));
        userRepository.saveOrUpdate(new User(null, "User 3", "user3@gmail.com"));

        sce.getServletContext().setAttribute("userRepository", userRepository);
    }
}
