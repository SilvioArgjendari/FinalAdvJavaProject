/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controller.BookController;
import datatier.persistence.entities.Book;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
//import javax.inject.Inject;
//import javax.inject.Named;
import java.util.stream.Collectors;
//import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Argjendari
 */
//@Named("bookBean")
@ManagedBean(name="bookBean")
@SessionScoped
public class BookBean implements Serializable {
//    @Inject
    private UserBean userBean;
    private List<Book> allBooks;
    private List<Book> recentlyAddedBooks;
    private List<Book> topRatedBooks;
    private List<Book> filteredBooks;
    
    private Book book;
    
    private String filterTitle;
    private String filterAuthor;
    private String lowerRange;
    private String upperRange;
    private String filterGenre;
    
    /**
     * Creates a new instance of BookBean
     */
    
    public BookBean() {
        loadList();
        mostRecentlyAdded();
        bestRated();
    }
    
//    @PostConstruct
//    public void init() {
//        loadList();
//    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(List<Book> allBooks) {
        this.allBooks = allBooks;
    }

    public List<Book> getRecentlyAddedBooks() {
        return recentlyAddedBooks;
    }

    public void setRecentlyAddedBooks(List<Book> recentlyAddedBooks) {
        this.recentlyAddedBooks = recentlyAddedBooks;
    }

    public List<Book> getTopRatedBooks() {
        return topRatedBooks;
    }

    public void setTopRatedBooks(List<Book> topRatedBooks) {
        this.topRatedBooks = topRatedBooks;
    }

    public boolean hasBook() {
        return !allBooks.isEmpty();
    }
    
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Book> getFilteredBooks() {
        return filteredBooks;
    }

    public void setFilteredBooks(List<Book> filteredBooks) {
        this.filteredBooks = filteredBooks;
    }

    public String getFilterTitle() {
        return filterTitle;
    }

    public void setFilterTitle(String filterTitle) {
        this.filterTitle = filterTitle;
    }

    public String getFilterAuthor() {
        return filterAuthor;
    }

    public void setFilterAuthor(String filterAuthor) {
        this.filterAuthor = filterAuthor;
    }

    public String getLowerRange() {
        return lowerRange;
    }

    public void setLowerRange(String lowerRange) {
        this.lowerRange = lowerRange;
    }

    public String getUpperRange() {
        return upperRange;
    }

    public void setUpperRange(String upperRange) {
        this.upperRange = upperRange;
    }

    public String getFilterGenre() {
        return filterGenre;
    }

    public void setFilterGenre(String filterGenre) {
        this.filterGenre = filterGenre;
    }
    
    
    
    public void loadList() {
        allBooks = BookController.getInstance().index();
    }
    
    public void reloadBook() {
        book = BookController.getInstance().show(book.getId());
    }
    
    public String actionReload() {
        loadList();
        return "index";
    }
    
    public void mostRecentlyAdded() {
        List<Book> lst = BookController.getInstance().index();
        
        recentlyAddedBooks = lst.stream()
                .sorted(Comparator.comparingInt((Book b) -> b.getId())
                        .reversed())
                .limit(5)
                .collect(Collectors.toList());   
    }
    
    public void bestRated() {
        List<Book> temp = BookController.getInstance().index();
        
        topRatedBooks = temp.stream()
                .sorted(Comparator.comparingDouble((Book b) -> b.getReviewList()
                                                                .stream()
                                                                .mapToDouble(review -> review.getReviewStar())
                                                                                                .average()
                                                                                                .orElse(0.0))
                                    .reversed())
                .limit(5)
                .collect(Collectors.toList());
    }
    
    public Double getAverageReview(Integer id) {
        Book book = BookController.getInstance().show(id);
        
        Double rate =  book.getReviewList()
                .stream()
                .mapToDouble(r -> r.getReviewStar())
                .average()
                .orElse(0.0);
        
        return Math.round(rate * 10.0) / 10.0;
    }
    
    
    private boolean paramIsPresent(String paramValue) {
        return paramValue != null && !paramValue.trim().isEmpty();
    }
    
    private Query constructQuery() {
        EntityManager em = Persistence
                .createEntityManagerFactory("FinalAdvJavaProjectPU")
                .createEntityManager();
        
        String ql = "SELECT b FROM Book b WHERE b.id = b.id ";
        if (paramIsPresent(filterTitle))
            ql += "AND LOWER(b.title) LIKE LOWER(:title)";
        if (paramIsPresent(filterAuthor))
            ql += "AND LOWER(b.author) LIKE LOWER(:author)";
        if (paramIsPresent(filterGenre))
            ql += "AND LOWER(b.genre) LIKE LOWER(:genre)";
        
        Query query = em.createQuery(ql);
        
        if (paramIsPresent(filterTitle))
            query.setParameter("title", "%" + filterTitle + "%");
        if (paramIsPresent(filterAuthor))
            query.setParameter("author", "%" + filterAuthor + "%");
        if (paramIsPresent(filterGenre))
            query.setParameter("genre", "%" + filterGenre + "%");
        
        return query;
    }
    
    public String filter() {
        Query q = constructQuery();
        List<Book> list = q.getResultList();
        
        filteredBooks = list.stream()
                .filter(book -> getAverageReview(book.getId()) >= Integer.parseInt(lowerRange))
                .filter(book -> getAverageReview(book.getId()) <= Integer.parseInt(upperRange))
                .collect(Collectors.toList());
        
        return "";
    }
    
}
