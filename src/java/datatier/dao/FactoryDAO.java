package datatier.dao;

import datatier.persistence.entities.Book;
import datatier.persistence.entities.Review;

/**
 *
 * @author Argjendari
 */

public class FactoryDAO {

    private static final IDAO<Book> BOOK_DAO;
    private static final IDAO<Review> REVIEW_DAO;
    private static final UserIDAO USER_DAO;
    
    static {
        BOOK_DAO = new BookDAO();
        REVIEW_DAO = new ReviewDAO();
        USER_DAO = new UserDAO();
    }

    public static IDAO<Book> getBookDao() {
        return BOOK_DAO;
    }

    public static IDAO<Review> getReviewDao() {
        return REVIEW_DAO;
    }

    public static UserIDAO getUserDao() {
        return USER_DAO;
    }
    
    
    
    
}
