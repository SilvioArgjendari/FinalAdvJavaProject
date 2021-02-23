package datatier.dao;

import datatier.persistence.PersistenceUtil;
import datatier.persistence.entities.Review;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Argjendari
 */

public class ReviewDAO implements IDAO<Review> {

    @Override
    public List<Review> index() {
        return PersistenceUtil.getEntityManager().createNamedQuery("Review.findAll", Review.class).getResultList();
    }

    @Override
    public Review show(Object key) {
        return PersistenceUtil.getEntityManager().find(Review.class, key);
    }

    @Override
    public void store(Review review) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(review);
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
    public void update(Review review) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(review);
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
    public void destoy(Review review) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(review);
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
