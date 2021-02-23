package api;

import datatier.persistence.entities.Book;
import java.util.Date;

/**
 *
 * @author Argjendari
 */

public class BookData {
    private String title;
    private String genre;
    private String synopsis;
    private Date publicationYear;
    private Date dataAdded;

    public BookData(Book book) {
        this.title = book.getTitle();
        this.genre = book.getGenre();
        this.synopsis = book.getSynopsis();
        this.publicationYear = book.getPublicationYear();
        this.dataAdded = book.getDateAdded();
    }
    
    

}
