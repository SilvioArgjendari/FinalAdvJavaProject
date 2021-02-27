/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controller.UserController;
import datatier.persistence.entities.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Argjendari
 */
@ManagedBean(name = "userDetailBean")
@RequestScoped
public class UserDetailBean {

    private int id;
    private User user;
    
    /**
     * Creates a new instance of UserDetailBean
     */
    public UserDetailBean() {
    }
    
    public void init() {
        user = UserController.getInstance().show(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
}
