package datatier.dao;

import datatier.persistence.PersistenceUtil;
import datatier.persistence.entities.User;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Argjendari
 */

public class UserDAO implements UserIDAO {

    @Override
    public User authenticateUser(String email, String password) {
        return PersistenceUtil.getEntityManager()
                .createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
    }

    @Override
    public List<User> index() {
        return PersistenceUtil.getEntityManager().createNamedQuery("User.findAll", User.class).getResultList();
    }

    @Override
    public User show(Object key) {
        return PersistenceUtil.getEntityManager().find(User.class, key);
    }

    @Override
    public void store(User user) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
        }
        finally {
            if (entityManager.isOpen())
                entityManager.close();
        }
    }

    @Override
    public void update(User user) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
        }
        finally {
            if (entityManager.isOpen())
                entityManager.close();
        }
    }

    @Override
    public void destoy(User user) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
        }
        finally {
            if (entityManager.isOpen())
                entityManager.close();
        }
    }

}
