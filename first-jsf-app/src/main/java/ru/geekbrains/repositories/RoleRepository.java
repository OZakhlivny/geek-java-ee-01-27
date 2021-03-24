package ru.geekbrains.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.entities.Role;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RoleRepository {

    private final Logger logger = LoggerFactory.getLogger(RoleRepository.class);

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    public Role saveOrUpdate(Role role) {
        if (role.getId() == null){
            em.persist(role);
            return role;
        }
        else return em.merge(role);
    }

    public Role findById(Long id) {
        return em.find(Role.class, id);
    }

    public List<Role> getAllRoles() {
        return em.createNamedQuery("findAllRoles", Role.class).getResultList();
    }

    public void deleteById(Long id) {
        em.createNamedQuery("deleteByIdRole").setParameter("id", id).executeUpdate();
    }

    public Long getCount() {
        return em.createNamedQuery("countAllRoles", Long.class).getSingleResult();
    }
}
