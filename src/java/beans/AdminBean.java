/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controller.BookController;
import controller.UserController;
import datatier.persistence.entities.Book;
import datatier.persistence.entities.User;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Argjendari
 */
@ManagedBean(name = "adminBean")
@SessionScoped
public class AdminBean implements Serializable {

    private String filterUserName;
    private String filterBookTitle;
    
    private List<Book> bookList;
    private List<User> userList;
    
    private User user;
    private Book book;
    /**
     * Creates a new instance of AdminBean
     */
    public AdminBean() {
        loadBooks();
        loadUsers();
    }

    public String getFilterUserName() {
        return filterUserName;
    }

    public void setFilterUserName(String filterUserName) {
        this.filterUserName = filterUserName;
    }

    public String getFilterBookTitle() {
        return filterBookTitle;
    }

    public void setFilterBookTitle(String filterBookName) {
        this.filterBookTitle = filterBookName;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
    public String filterBook() {
        List<Book> temp = BookController.getInstance().index();
        
        bookList = temp.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(filterBookTitle.toLowerCase()))
                .collect(Collectors.toList());
        
        return "";
    }
    
    public String filterUser() {
        List<User> tempp = UserController.getInstance().index();
        
        userList = tempp.stream()
                .filter(user -> user.getName().toLowerCase().contains(filterUserName.toLowerCase()))
                .collect(Collectors.toList());
        
        return "";
    }
    
    public void loadBooks() {
        bookList = BookController.getInstance().index();
    }
    
    public void loadUsers() {
        userList = UserController.getInstance().index();
    }
    
    public void removeBook(Integer id) {
        Book book = BookController.getInstance().show(id);
        BookController.getInstance().destroy(book);
        
        loadBooks();
    }
    
    public void removeUser(Integer id) {
        User user = UserController.getInstance().show(id);
        UserController.getInstance().destroy(user);
        
        loadUsers();
    }
    
    
    
}
