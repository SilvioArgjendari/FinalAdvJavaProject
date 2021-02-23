/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Argjendari
 */
public interface IController<T> {
    
    List<T> index();
    
    T show(Object key);

    void store(T entity);

    void update(T entity);

    void destroy(T entity);
    
}
