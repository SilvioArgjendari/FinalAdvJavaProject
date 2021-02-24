/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Argjendari
 */
//@Named(value = "addUserBean")
@ManagedBean
@RequestScoped
public class AddUserBean {

    /**
     * Creates a new instance of AddUserBean
     */
    public AddUserBean() {
    }
    
}
