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
import java.util.stream.Collectors;
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
    @WebMethod(operationName = "getBooks")
    public List<Book> getBooks(@WebParam(name = "title") String title, @WebParam(name = "author") String author, 
            @WebParam(name = "genre") String genre, @WebParam(name = "min") String min, @WebParam(name = "max") String max) {
        
        List<Book> response = new ArrayList<>();
        
        List<Book> allBooks = BookController.getInstance().index();
        
        response = allBooks.stream().filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(b -> b.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .filter(b -> b.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .filter(b -> getAverageReview(b.getId()) >= Integer.parseInt(min))
                .filter(b -> getAverageReview(b.getId()) <= Integer.parseInt(max))
                .collect(Collectors.toList());
        
        return response;
    }
    
    
    public Double getAverageReview(Integer id) {
        Book book = BookController.getInstance().show(id);
        
        Double rate =  book.getReviewList()
                .stream()
                .mapToDouble(r -> r.getReviewStar())
                .average()
                .orElse(0.0);
        
        return Math.round(rate * 10.0) / 10.0;
    }
    
}
