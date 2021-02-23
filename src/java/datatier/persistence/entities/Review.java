package datatier.persistence.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Argjendari
 */

@Entity
@Table(name = "reviews")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r")
    , @NamedQuery(name = "Review.findByReviewerId", query = "SELECT r FROM Review r WHERE r.reviewPK.reviewerId = :reviewerId")
    , @NamedQuery(name = "Review.findByBookId", query = "SELECT r FROM Review r WHERE r.reviewPK.bookId = :bookId")
    , @NamedQuery(name = "Review.findByReviewStar", query = "SELECT r FROM Review r WHERE r.reviewStar = :reviewStar")
    , @NamedQuery(name = "Review.findByComment", query = "SELECT r FROM Review r WHERE r.comment = :comment")})
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReviewPK reviewPK;
    @Column(name = "review_star")
    private Integer reviewStar;
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "book_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Book book;
    @JoinColumn(name = "reviewer_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Review() {
    }

    public Review(ReviewPK reviewPK) {
        this.reviewPK = reviewPK;
    }

    public Review(int reviewerId, int bookId) {
        this.reviewPK = new ReviewPK(reviewerId, bookId);
    }

    public ReviewPK getReviewPK() {
        return reviewPK;
    }

    public void setReviewPK(ReviewPK reviewPK) {
        this.reviewPK = reviewPK;
    }

    public Integer getReviewStar() {
        return reviewStar;
    }

    public void setReviewStar(Integer reviewStar) {
        this.reviewStar = reviewStar;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewPK != null ? reviewPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.reviewPK == null && other.reviewPK != null) || (this.reviewPK != null && !this.reviewPK.equals(other.reviewPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datatier.persistence.entities.Review[ reviewPK=" + reviewPK + " ]";
    }

}
