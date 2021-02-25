/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import controller.BookController;
import datatier.persistence.entities.Book;
import java.util.Date;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Argjendari
 */
@ManagedBean(name = "createBookBean")
@RequestScoped
public class CreateBookBean {

    @ManagedProperty(value="#{adminBean}")
    private AdminBean adminBean;
    
    private int bookId;
    private String title;
    private String author;
    private String publishingHouse;
    private Date publicationYear;
    private String genre;
    private Date dateAdded;
    private String synopsis;
    private String thumbnail;
    
    /**
     * Creates a new instance of CreateBookBean
     */
    public CreateBookBean() {
    }
    
    private Book initBook() {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublishingHouse(publishingHouse);
        book.setPublicationYear(publicationYear);
        book.setGenre(genre);
        book.setDateAdded(dateAdded);
        book.setSynopsis(synopsis);
        book.setThumbnail(thumbnail);
        return book;
    }
    
    public String create() {
        Book book = initBook();
        BookController.getInstance().store(book);
        
        adminBean.loadBooks();
        return "admin-panel";
    }
    
    

    public AdminBean getAdminBean() {
        return adminBean;
    }

    public void setAdminBean(AdminBean adminBean) {
        this.adminBean = adminBean;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public Date getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Date publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    
    
}
