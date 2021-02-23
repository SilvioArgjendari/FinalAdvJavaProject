package controller;

import datatier.dao.FactoryDAO;
import datatier.persistence.entities.User;
import java.util.List;

/**
 *
 * @author Argjendari
 */

public class UserController implements IController<User> {

    static final UserController INSTANCE;
    
    static {
        INSTANCE = new UserController();
    }

    private UserController() {
    }
    
    public static UserController getInstance() {
        return INSTANCE;
    }
    
    
    @Override
    public List<User> index() {
        return FactoryDAO.getUserDao().index();
    }

    @Override
    public User show(Object key) {
        return FactoryDAO.getUserDao().show(key);
    }

    @Override
    public void store(User user) {
        FactoryDAO.getUserDao().store(user);
    }

    @Override
    public void update(User user) {
        FactoryDAO.getUserDao().update(user);
    }

    @Override
    public void destroy(User user) {
        FactoryDAO.getUserDao().destoy(user);
    }
    
    public User authenticateUser(String email, String password) {
        return FactoryDAO.getUserDao().authenticateUser(email, password);
    }

}
