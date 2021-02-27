/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controller.UserController;
import datatier.persistence.entities.User;
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
@ManagedBean(name = "updateUserBean")
@ViewScoped
public class UpdateUserBean {

    @ManagedProperty(value="#{adminBean}")
    private AdminBean adminBean;
    
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String type;
    
    
    /**
     * Creates a new instance of UpdateUserBean
     */
    public UpdateUserBean() {
    }
    
    @PostConstruct
    public void init() {
        Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.id = Integer.parseInt(map.get("id"));
        User user = UserController.getInstance().show(id);
        
        
    }
    
    public void populateBeanFromModel(User user) {
        id = user.getId();
        name = user.getName();
        surname = user.getSurname();
        password = user.getPassword();
        type = user.getType();
    }
    
    public void populateModelFromBean(User user) {
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setType(type);
    }
    
    public void update() {
        User user = UserController.getInstance().show(id);
        populateModelFromBean(user);
        UserController.getInstance().update(user);
        
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("confirmationMessage", "Book saved!");
        adminBean.loadUsers();
    }

//    public AdminBean getAdminBean() {
//        return adminBean;
//    }

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
