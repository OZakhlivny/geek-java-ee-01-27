package repositories;
import entities.User;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class UserRepository {
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
                this.saveOrUpdate(new User(null, "User 1", "user1@gmail.com"));
                this.saveOrUpdate(new User(null, "User 2", "user2@gmail.com"));
                this.saveOrUpdate(new User(null, "User 3", "user3@gmail.com"));
                ut.commit();
            } catch (Exception ex) {
                logger.error("", ex);
                ut.rollback();
            }
        }
    }

    public List<User> findAll() {
        return em.createNamedQuery("findAllUsers", User.class).getResultList();
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Transactional
    public void saveOrUpdate(User user) {
        if (user.getId() == null) em.persist(user);
        else em.merge(user);
    }

    @Transactional
    public void deleteById(Long id) {
        em.createNamedQuery("deleteByIdUser").setParameter("id", id).executeUpdate();
    }

    public Long countAll() {
        return em.createNamedQuery("countAllUsers", Long.class).getSingleResult();
    }
}
