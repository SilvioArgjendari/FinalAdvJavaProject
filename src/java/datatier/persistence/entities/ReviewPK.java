package datatier.persistence.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Argjendari
 */

@Embeddable
public class ReviewPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "reviewer_id")
    private int reviewerId;
    @Basic(optional = false)
    @Column(name = "book_id")
    private int bookId;

    public ReviewPK() {
    }

    public ReviewPK(int reviewerId, int bookId) {
        this.reviewerId = reviewerId;
        this.bookId = bookId;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) reviewerId;
        hash += (int) bookId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReviewPK)) {
            return false;
        }
        ReviewPK other = (ReviewPK) object;
        if (this.reviewerId != other.reviewerId) {
            return false;
        }
        if (this.bookId != other.bookId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datatier.persistence.entities.ReviewPK[ reviewerId=" + reviewerId + ", bookId=" + bookId + " ]";
    }

}
