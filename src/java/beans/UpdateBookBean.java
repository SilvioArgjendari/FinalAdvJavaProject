/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import controller.BookController;
import datatier.persistence.entities.Book;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Argjendari
 */
@ManagedBean(name = "updateBookBean")
@ViewScoped
public class UpdateBookBean {
    
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
     * Creates a new instance of UpdateBookBean
     */
    public UpdateBookBean() {
    }
    
    @PostConstruct
    public void init() {
        Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.bookId = Integer.parseInt(map.get("bookId"));
        Book book = BookController.getInstance().show(bookId);
        populateBeanFromModel(book);
    }
    
    public void populateBeanFromModel(Book book) {
        bookId = book.getId();
        title = book.getTitle();
        author = book.getAuthor();
        publicationYear = book.getPublicationYear();
        publishingHouse = book.getPublishingHouse();
        genre = book.getGenre();
        dateAdded = book.getDateAdded();
        synopsis = book.getSynopsis();
        thumbnail = book.getThumbnail();
    }
    
    public void populateModelFromBean(Book book) {
        book.setId(bookId);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublicationYear(publicationYear);
        book.setPublishingHouse(publishingHouse);
        book.setGenre(genre);
        book.setDateAdded(dateAdded);
        book.setSynopsis(synopsis);
        book.setThumbnail(thumbnail);
    }
    
    public void update() {
        Book book = BookController.getInstance().show(bookId);
        populateModelFromBean(book);
        BookController.getInstance().update(book);
        
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("confirmationMessage", "Book saved!");
//        adminBean.loadBooks();
    }

//    public AdminBean getAdminBean() {
//        return adminBean;
//    }

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
