/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controller.BookController;
import datatier.dao.BookDAO;
import datatier.dao.FactoryDAO;
import datatier.dao.IDAO;
import datatier.persistence.entities.Book;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Argjendari
 */
//@Named("bookBean")
@ManagedBean(name="bookBean")
@SessionScoped
public class BookBean implements Serializable {
//    @Inject
    private List<Book> allBooks;
    private List<Book> recentlyAddedBooks;
    private List<Book> topRatedBooks;
//    private Book book;
    
    /**
     * Creates a new instance of BookBean
     */
    
    public BookBean() {
        loadList();
        mostRecentlyAdded();
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
    
//    public Book getBook() {
//        return book;
//    }
//
//    public void setBook(Book book) {
//        this.book = book;
//    }
    
    

//    public Book getBook() {
//        return book;
//    }
//
//    public void setBook(Book book) {
//        this.book = book;
//    }
    
    
    
    public void loadList() {
        allBooks = BookController.getInstance().index();
    }
    
//    public void reloadBook() {
//        book = BookController.getInstance().show(book.getId());
//    }
    
    public void mostRecentlyAdded() {
        List<Book> lst = BookController.getInstance().index();
        
        recentlyAddedBooks = lst.stream()
                .sorted(Comparator.comparingInt((Book b) -> b.getId())
                        .reversed())
                .limit(2)
                .collect(Collectors.toList());
        
    }
}
