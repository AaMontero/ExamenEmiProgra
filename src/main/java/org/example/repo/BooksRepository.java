package org.example.repo;

import io.lettuce.core.RedisClient;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.db.Books;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BooksRepository implements PanacheRepositoryBase<Books, Long> {
    @Inject
    RedisClient redisClient;

    @Produces(MediaType.APPLICATION_JSON)

    //Método para encontrar el libro por ID
    public Optional<Books> encontrarLibro(Long id){
        try {
            Optional<Books> book = this.findByIdOptional(id);
            if(book.isPresent()){
                return book;
            }else{
                return null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return this.findByIdOptional(id);
    }

    //Método para listar todos los libros
    public List<Books> listarLibros(){
        try {
            PanacheQuery<Books> query = this.findAll();
            List<Books> libros = query.list();
            return libros;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @Transactional
    public void agregarLibro(Books book){
        try{

            persist(book);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    @Transactional
    public void actualizarLibro(Books book){
        try{
            Optional<Books> bookFind =   this.findByIdOptional(book.getId());
            if(bookFind!=null){
                persistAndFlush(book);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @Transactional
    public void borrarLibro(long id){
        try {
            deleteById(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
