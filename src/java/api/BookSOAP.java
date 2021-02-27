/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import controller.BookController;
import datatier.persistence.entities.Book;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Argjendari
 */
@WebService(serviceName = "BookSOAP")
public class BookSOAP {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public List<Book> getBooks(@WebParam(name = "title") String title, @WebParam(name = "author") String author, 
            @WebParam(name = "genre") String genre, @WebParam(name = "min") String min, @WebParam(name = "max") String max) {
        
        List<Book> response = new ArrayList<>();
        
        List<Book> allBooks = BookController.getInstance().index();
        
//        allBooks.stream().
        
        
        
        return response;
    }
}
