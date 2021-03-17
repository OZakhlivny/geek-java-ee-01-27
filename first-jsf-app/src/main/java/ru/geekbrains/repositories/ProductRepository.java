package ru.geekbrains.repositories;
import ru.geekbrains.entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class ProductRepository {
    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public List<Product> findAll() {
        return em.createNamedQuery("findAllProducts", Product.class).getResultList();
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public Product findByName(String name){
        System.out.println(name);
        List<Product> resultQuery = em.createNamedQuery("findByNameProduct", Product.class)
                                        .setParameter("name", name).getResultList();
        if(resultQuery.isEmpty()) return null;
        else return resultQuery.get(0);
    }

    public List<Product> findByCategoryId(Long categoryId){
        return em.createNamedQuery("findByCategoryId", Product.class)
                 .setParameter("category_id", categoryId).getResultList();
    }

    public void saveOrUpdate(Product product) {
        if (product.getId() == null) em.persist(product);
        else em.merge(product);
    }

    public void deleteById(Long id) {
        em.createNamedQuery("deleteByIdProduct").setParameter("id", id).executeUpdate();
    }

    public Long countAll() {
        return em.createNamedQuery("countAllProducts", Long.class).getSingleResult();
    }
}
