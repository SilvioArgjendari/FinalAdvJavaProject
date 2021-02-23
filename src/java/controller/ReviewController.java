package controller;

import datatier.dao.FactoryDAO;
import datatier.persistence.entities.Review;
import java.util.List;

/**
 *
 * @author Argjendari
 */

public class ReviewController implements IController<Review> {

    private static final ReviewController INSTANCE;
    
    static {
        INSTANCE = new ReviewController();
    }
    
    private ReviewController() {
    }
    
    public static ReviewController getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Review> index() {
        return FactoryDAO.getReviewDao().index();
    }

    @Override
    public Review show(Object key) {
        return FactoryDAO.getReviewDao().show(key);
    }

    @Override
    public void store(Review review) {
        FactoryDAO.getReviewDao().store(review);
    }

    @Override
    public void update(Review review) {
        FactoryDAO.getReviewDao().update(review);
    }

    @Override
    public void destroy(Review review) {
        FactoryDAO.getReviewDao().destoy(review);
    }
    
    
}
