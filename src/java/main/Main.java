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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
//        app.allBooks();
//        app.go();
//        app.findABook();        // Works
//        app.getAuthedUser();    // Works
//        app.allreviews();       // Works
//        app.findReviewsOfFirstBook();   // Works
//        app.oldMethod();      // Works
//        app.deleteUser();
//        app.getBestRatedBooks();
//        app.getAllReviewsOfAUser();
        app.latestBooks();
//        System.out.println(app.getAverageReview(2));
        
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
        System.out.println(user.getType());
    }
    
    private void oldMethod() {
        EntityManager em = PersistenceUtil.getEntityManager();
        List<Book> listOfBooks = em.createQuery("select b from Book b", Book.class).getResultList();
        listOfBooks.forEach(book -> {
            System.out.println("----- " + book.getTitle() + " Reviews -----");
            book.getReviewList().forEach(r -> System.out.println(r.getReviewStar()));
        });
    }
    
    private void deleteUser() {
        User user = UserController.getInstance().show(5);
        UserController.getInstance().destroy(user);
    }
    
    private void getBestRatedBooks() {
        List<Book> temp = bC.index();
        
        bookList = temp.stream()
                .sorted(Comparator.comparingDouble((Book b) -> b.getReviewList()
                                                                .stream()
                                                                .mapToDouble(review -> review.getReviewStar())
                                                                                                .average()
                                                                                                .orElse(0.0))
                        .reversed())
                .limit(2)
                .collect(Collectors.toList());
    }
    
    public Double getAverageReview(Integer id) {
        Book book = BookController.getInstance().show(id);
        
        return book.getReviewList()
                .stream()
                .mapToDouble(r -> r.getReviewStar())
                .average()
                .orElse(0.0);
        
    }
    
    public void getAllReviewsOfAUser() {
        List<Review> temp = ReviewController.getInstance().index();
        
        reviewList = temp.stream()
                .filter(review -> review.getReviewPK().getReviewerId() == 2)
                .collect(Collectors.toList());
        
        reviewList.forEach(review -> 
                                System.out.println(review.getUser().getName() 
                                        + " has made a review of "
                                        + review.getReviewStar() 
                                        + " to " + review.getBook().getTitle()));
    }
    
    private void latestBooks() {
        List<Book> dummy = BookController.getInstance().index();
        
        bookList = dummy.stream()
                .sorted(Comparator.comparingDouble((Book b) -> b.getId()).reversed())
                .limit(5)
                .collect(Collectors.toList());
        
        bookList.forEach(b -> System.out.println(b.getTitle()));
    }
    
    
//    private void updateAllOrders(String customerId) {
//        EntityManager em = PersistenceUtil.getEntityManager();
//        List<Orders> ordList = em.merge(em);
//        
//        ordList.stream()
//                .filter(order -> order.customer_id == customerId)
//                .map(ord -> ord.supplier.toUpperCase())
//        
//    }
    
//    public void CustomerResponse readCustomer(RequestData request) {
//        List<Oders> ordersList = em.createQuery("select o from Orders o where o.customer_id = :cId")
//                .setParameter("cId", request.customerId)
//                .getResultList();
//        ordersList
//                .stream
//                .filter(order -> order.getOrder_date.before(request.after) && order.getOrder_date.after(request.before))
//                .collect(toList);
//        
//        CustomerData customerData = new CustomerData();
//        customerData.setFullName();
//        customerData.setOrderNumber(allOders);
//        customerData.setAllMoney(sum);
//        
//        String fullCustName = odersList.get(0).getCustomer().getName + odersList.get(0).getCustomer().getSurname;
//        int allOrders = ordersList.stream().count();
//        double allMoney = ordersList.stream().mapToDouble(o -> o.getAmount()).sum();
//        
//        CustomerResponse response = new CustomerResponse;
//        response.setCustomerData(customerData);
//        
//        
//        return response;
//    }

}
