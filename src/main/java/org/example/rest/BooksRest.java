package org.example.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.db.Books;
import org.example.repo.BooksRepository;
import java.util.List;
import java.util.Optional;


@Path("/books")
public class BooksRest {
    @Inject
    BooksRepository repo;
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Books> getBooksById(@PathParam("id") Long id){
        return repo.encontrarLibro(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Books> getBooks(){
        return repo.listarLibros();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBooks(Books book){
        repo.agregarLibro(book);
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarLibro(Books libro) {
        repo.actualizarLibro(libro);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") Long id){
        repo.borrarLibro(id);
        return Response.status(Response.Status.NO_CONTENT).build();

    }

}
