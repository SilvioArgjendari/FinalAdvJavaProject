/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controller.UserController;
import datatier.persistence.entities.User;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Argjendari
 */
@ManagedBean(name = "createUserBean")
@RequestScoped
public class CreateUserBean {

    @ManagedProperty(value="#{adminBean}")
    private AdminBean adminBean;
    
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String type;
    
    /**
     * Creates a new instance of CreateUserBean
     */
    public CreateUserBean() {
    }
    
    private User initUser() {
        User user = new User();
        
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setType(type);
        return user;
    }
    
    public String create() {
        User user = initUser();
        
        UserController.getInstance().store(user);
        adminBean.loadUsers();
        
        return "admin/admin-panel";
    }

    public AdminBean getAdminBean() {
        return adminBean;
    }

    public void setAdminBean(AdminBean adminBean) {
        this.adminBean = adminBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    
    
    
}
