package datatier.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Argjendari
 */

@Entity
@Table(name = "books")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
    , @NamedQuery(name = "Book.findById", query = "SELECT b FROM Book b WHERE b.id = :id")
    , @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title")
    , @NamedQuery(name = "Book.findByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author")
    , @NamedQuery(name = "Book.findByPublishingHouse", query = "SELECT b FROM Book b WHERE b.publishingHouse = :publishingHouse")
    , @NamedQuery(name = "Book.findByPublicationYear", query = "SELECT b FROM Book b WHERE b.publicationYear = :publicationYear")
    , @NamedQuery(name = "Book.findByGenre", query = "SELECT b FROM Book b WHERE b.genre = :genre")
    , @NamedQuery(name = "Book.findByDateAdded", query = "SELECT b FROM Book b WHERE b.dateAdded = :dateAdded")
    , @NamedQuery(name = "Book.findBySynopsis", query = "SELECT b FROM Book b WHERE b.synopsis = :synopsis")
    , @NamedQuery(name = "Book.findByThumbnail", query = "SELECT b FROM Book b WHERE b.thumbnail = :thumbnail")})
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "author")
    private String author;
    @Basic(optional = false)
    @Column(name = "publishing_house")
    private String publishingHouse;
    @Basic(optional = false)
    @Column(name = "publication_year")
    @Temporal(TemporalType.DATE)
    private Date publicationYear;
    @Basic(optional = false)
    @Column(name = "genre")
    private String genre;
    @Basic(optional = false)
    @Column(name = "date_added")
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @Column(name = "synopsis")
    private String synopsis;
    @Column(name = "thumbnail")
    private String thumbnail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book", fetch=FetchType.EAGER)
    private List<Review> reviewList;

    public Book() {
    }

    public Book(Integer id) {
        this.id = id;
    }

    public Book(Integer id, String title, String author, String publishingHouse, Date publicationYear, String genre, Date dateAdded) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.dateAdded = dateAdded;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @XmlTransient
    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datatier.persistence.entities.Book[ id=" + id + " ]";
    }

}
