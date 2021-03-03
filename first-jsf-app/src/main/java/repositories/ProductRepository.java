package repositories;
import entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository {
    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() throws SystemException {
        if (countAll() == 0) {
            try {
                ut.begin();
                saveOrUpdate(new Product(null, "Product  1",
                        "Description of product 1", new BigDecimal(100), null));
                saveOrUpdate(new Product(null, "Product  2",
                        "Description of product 2", new BigDecimal(200), null));
                saveOrUpdate(new Product(null, "Product  3",
                        "Description of product 3", new BigDecimal(300), null));
                ut.commit();
            } catch (Exception ex) {
                logger.error("", ex);
                ut.rollback();
            }
        }
    }

    public List<Product> findAll() {
        return em.createNamedQuery("findAllProducts", Product.class).getResultList();
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    @Transactional
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) em.persist(product);
        else em.merge(product);
    }

    @Transactional
    public void deleteById(Long id) {
        em.createNamedQuery("deleteByIdProduct").setParameter("id", id).executeUpdate();
    }

    public Long countAll() {
        return em.createNamedQuery("countAllProducts", Long.class).getSingleResult();
    }
}
