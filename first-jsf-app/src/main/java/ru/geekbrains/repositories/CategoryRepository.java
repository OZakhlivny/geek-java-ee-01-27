package ru.geekbrains.repositories;
import ru.geekbrains.entities.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class CategoryRepository {
    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;


    public List<Category> findAll() {
        return em.createNamedQuery("findAllCategories", Category.class).getResultList();
    }

    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    public void saveOrUpdate(Category category) {
        if (category.getId() == null) em.persist(category);
        else em.merge(category);
    }

    public void deleteById(Long id) {
        em.createNamedQuery("deleteByIdCategory").setParameter("id", id).executeUpdate();
    }

    public Long countAll() {
        return em.createNamedQuery("countAllCategories", Long.class).getSingleResult();
    }

    public Category getReference(Long id) {
        return em.getReference(Category.class, id);
    }
}
