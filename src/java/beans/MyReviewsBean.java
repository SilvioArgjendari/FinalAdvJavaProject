/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import controller.ReviewController;
import datatier.persistence.entities.Review;
import datatier.persistence.entities.User;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Argjendari
 */
@ManagedBean(name = "myReviewsBean")
@RequestScoped
public class MyReviewsBean implements Serializable {

    private int userId;
    private int bookId;
    private int reviewStar;
    private String comment;
    
    private List<Review> myReviewsList;
    
    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;
    
    /**
     * Creates a new instance of MyReviewsBean
     */
    public MyReviewsBean() {
//        loadList();
    }
    
    public List<Review> loadList(Integer id) {
        List<Review> temp = ReviewController.getInstance().index();
        
        return temp
                .stream()
                .filter(r -> r.getReviewPK().getReviewerId() == id)
                .collect(Collectors.toList());
    }
    

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getReviewStar() {
        return reviewStar;
    }

    public void setReviewStar(int reviewStar) {
        this.reviewStar = reviewStar;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Review> getMyReviewsList() {
        return myReviewsList;
    }

    public void setMyReviewsList(List<Review> myReviewsList) {
        this.myReviewsList = myReviewsList;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    
}
