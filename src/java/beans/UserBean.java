/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controller.UserController;
import datatier.persistence.entities.User;
//import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Argjendari
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private User currentUser;
//    private User newUser;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String type;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
//        clearAttributes();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean hasCurrentUser() {
        return currentUser != null;
    }
    
    public boolean isAdmin() {
        if (hasCurrentUser())
            return currentUser.getType().equals("admin");
        return false;
    }
    
    public boolean isStandard() {
        if (hasCurrentUser())
            return currentUser.getType().equals("standard");
        return false;
    }
    

//    public User getNewUser() {
//        return newUser;
//    }
//
//    public void setNewUser(User newUser) {
//        this.newUser = newUser;
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void clearAttributes() {
        currentUser = null;
        name = "";
        surname = "";
        email = "";
        password = "";
    }

    public String updateProfile() {
        currentUser.setName(name);
        currentUser.setSurname(surname);
        currentUser.setEmail(email);
        currentUser.setPassword(password);

        UserController.getInstance().update(currentUser);

        return "index";
    }

    public String login() {
        try {
            currentUser = UserController.getInstance().authenticateUser(email, password);
            if (currentUser.equals(null)) {
                return "login";
            }
            if (currentUser.getType().equals("admin")) {
                return "admin/admin-panel";
            }
            return "index";
        } catch (Exception e) {
            return null;
        }
        
    }

    public String logout() {
        clearAttributes();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";
    }
    
    public String logoutAdmin() {
        clearAttributes();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index";
    }

    public String goToProfile() {
        return "login";
    }

}
