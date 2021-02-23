package controller;

import datatier.dao.FactoryDAO;
import datatier.persistence.entities.Book;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Argjendari
 */

public class BookController implements IController<Book> {

    static final BookController INSTANCE;
    
    static {
        INSTANCE = new BookController();
    }
    
    private BookController() {
    } 
    
    public static BookController getInstance() {
        return INSTANCE;
    }
    
    @Override
    public List<Book> index() {
        return FactoryDAO.getBookDao().index();
    }

    @Override
    public Book show(Object key) {
        return FactoryDAO.getBookDao().show(key);
    }

    @Override
    public void store(Book book) {
        FactoryDAO.getBookDao().store(book);
    }

    @Override
    public void update(Book book) {
        FactoryDAO.getBookDao().update(book);
    }

    @Override
    public void destroy(Book book) {
        FactoryDAO.getBookDao().destoy(book);
    }

}
