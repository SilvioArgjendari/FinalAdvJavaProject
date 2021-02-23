package datatier.dao;

import datatier.persistence.PersistenceUtil;
import datatier.persistence.entities.Book;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Argjendari
 */

public class BookDAO implements IDAO<Book>{

    @Override
    public List<Book> index() {
        return PersistenceUtil.getEntityManager().createNamedQuery("Book.findAll", Book.class).getResultList();
    }

    @Override
    public Book show(Object key) {
        return PersistenceUtil.getEntityManager().find(Book.class, key);
    }

    @Override
    public void store(Book book) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(book);
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
    public void update(Book book) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(book);
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
    public void destoy(Book book) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (!entityManager.contains(book)) {
                book = entityManager.merge(book);
            }
            entityManager.remove(book);
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
