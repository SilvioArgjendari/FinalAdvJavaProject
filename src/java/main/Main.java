package main;

import controller.BookController;
import controller.ReviewController;
import controller.UserController;
import datatier.dao.BookDAO;
import datatier.dao.FactoryDAO;
import datatier.dao.IDAO;
import datatier.persistence.PersistenceUtil;
import datatier.persistence.entities.Book;
import datatier.persistence.entities.Review;
import datatier.persistence.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Argjendari
 */

public class Main {
    
    BookController bC = BookController.getInstance();
    ReviewController rC = ReviewController.getInstance();
    UserController uC = UserController.getInstance();
    
    List<Book> bookList = new ArrayList<>();
    List<Review> reviewList = new ArrayList<>();
    
    public static void main(String[] args) {
        Main app = new Main();
        app.allBooks();
//        app.go();
//        app.findABook();        // Works
//        app.getAuthedUser();    // Works
//        app.allreviews();       // Works
//        app.findReviewsOfFirstBook();   // Works
//        app.oldMethod();      // Works
        
    }
    
    private void go() {
        bookList = bC.index();
        
        bookList.stream().forEach(book -> {
            System.out.println("----- " + book.getTitle() + " Reviews -----");
//            Integer sum = 0;
//            for (Review rev : book.getReviewList()) {
//               sum = rev.getReviewStar();
//            }
//            System.out.println(sum/book.getReviewList().size());
                
            Double avgStar = book.getReviewList()
                    .stream()
                    .mapToInt(Review::getReviewStar)
                    .average()
                    .orElse(0);
            System.out.println(avgStar);

//            book.getReviewList()
//                    .stream()
//                    .forEach(r -> System.out.println(r.getReviewStar()));

//            List<Review> r = book.getReviewList();
//            System.out.println(r.size());

//            Double r = book.getReviewList() 
//                    .stream()
//                    .mapToInt(Review::getReviewStar)
//                    .average()
//                    .orElse(0);
//            System.out.println(r);
        });
    }
    
    private void allBooks() {
        bookList = bC.index();
        for (Book book : bookList) {
            System.out.println(book.getTitle());
        }
    }
    
    private void allreviews() {
        System.out.println();
        
        reviewList = rC.index();
        reviewList.forEach(r -> System.out.println(r.getReviewStar()));
    }
    
    private void findReviewsOfFirstBook() {
        System.out.println("Reviews of first book(2): ");
        Book book = bC.show(1);
        book.getReviewList().forEach(review -> System.out.println(review.getReviewStar()));
    }
    
    private void findABook() {
        Book book = bC.show(3);
        System.out.println(book.getTitle());
    }
    
    private void getAuthedUser() {
        User user = uC.authenticateUser("silvio@email.com", "silvio123");
        System.out.println(user.getName());
    }
    
    private void oldMethod() {
        EntityManager em = PersistenceUtil.getEntityManager();
        List<Book> listOfBooks = em.createQuery("select b from Book b", Book.class).getResultList();
        listOfBooks.forEach(book -> {
            System.out.println("----- " + book.getTitle() + " Reviews -----");
            book.getReviewList().forEach(r -> System.out.println(r.getReviewStar()));
        });
    }

}
