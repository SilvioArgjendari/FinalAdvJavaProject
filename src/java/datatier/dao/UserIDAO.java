/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatier.dao;

import datatier.persistence.entities.User;

/**
 *
 * @author Argjendari
 */
public interface UserIDAO extends IDAO<User> {
   
    User authenticateUser(String email, String password);
    
}
