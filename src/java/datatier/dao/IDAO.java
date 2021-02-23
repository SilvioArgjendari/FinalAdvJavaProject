/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatier.dao;

import java.util.List;

/**
 *
 * @author Argjendari
 */
public interface IDAO<T> {
    
    public List<T> index();
    
    public T show(Object key);
    
    public void store(T entity);
    
    public void update(T entity);
    
    public void destoy(T entity);
    
}
