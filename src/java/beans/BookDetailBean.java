/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controller.BookController;
import datatier.persistence.entities.Book;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Argjendari
 */
@ManagedBean(name = "bookDetailBean")
@RequestScoped
public class BookDetailBean {

    private int bookId;
    private Book book;
    
    /**
     * Creates a new instance of BookDetailBean
     */
    public BookDetailBean() {
    }
    
    public void init() {
        book = BookController.getInstance().show(bookId);
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
    
    
}
