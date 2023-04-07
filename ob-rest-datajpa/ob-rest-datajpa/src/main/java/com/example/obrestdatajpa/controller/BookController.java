package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entity.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
public class BookController {

    //Agregamos el log
    private final Logger log = LoggerFactory.getLogger(BookController.class); //Esto nos va a permitir mostrar mensajes con colores, niveles de error, etc.

    //Atributos

    private BookRepository bookRepository;

    //Constructores

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    //CRUD sobre la entidad Book

    /**
     * Buscar todos los libros que hay en base de datos (Arraylist de libros)
     * https://localhost:8081/api/books
     * @return
     */
    @ApiOperation("Get all the books from database")
    @GetMapping("/api/books")
    public List<Book> findAll() {
        //recuperar y devolver los libros de base de datos
        return bookRepository.findAll();
    }

    /**
     *https://localhost:8081/api/books/1
     * https://localhost:8081/api/books/2
     * Request
     * Response
     * @param id
     * @return
     */
    //Buscar un solo libro en base de datos según su id
    @GetMapping("api/books/{id}")
    public ResponseEntity <Book> findOneById(@PathVariable Long id) {

        Optional<Book> bookOpt = bookRepository.findById(id);
        // opción
        if (bookOpt.isPresent()){
            return ResponseEntity.ok(bookOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }

        // Opción 2
        //return bookOpt.orElse(null);


    }

    /**
     * Crear un nuevo libro en base de datos
     * Método POST no colisiona con FindAll porque son diferentes métodos HTTP: GET vs POST
     * @param book
     * @param headers
     * @return
     */
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        //Guardar el libro por parametro es la base de datos

        if(book.getId() != null){ //quiere decir que existe el id y por lo tanto no es una creación
            //Podemos agregar un warning o un log
            log.warn("trying to create a book with id");
            System.out.println("trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);//El libro devuelto tiene una clave primaria
    }



    /**
     * Actualizar un libro existente en base de datos
     */
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){

        if(book.getId() == null) { //Si no tiene id quiere decir que es una creación, no es actualización
            log.warn("Trying to update a non existent book.");
            return ResponseEntity.badRequest().build();
        }

        if(!bookRepository.existsById(book.getId())){
            log.warn("Trying to update a non existent book.");
            return ResponseEntity.notFound().build();
        }

        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);//El libro devuelto tiene una clave primaria
    }


    /**
     * Borrar libros por id
     * @param id
     * @return
     */
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){

        /*
        A nivel de persistencia borrar es lo que más probelmas nos puede dar, ya que cuando tenemos una base de datos,
        existe el concepto de integridad referencial (cuando una tabla apunta hacia otra con clave foránea)
        Si eliminamos algo que depende de otra cosa, se genera una excepción, por lo cual conviene capturar las
        excepciones
        )
         */
        if(!bookRepository.existsById(id)){
            log.warn("Trying to delete a non existent book.");
            return ResponseEntity.notFound().build();
        }

        bookRepository.deleteById(id);

        return ResponseEntity.noContent().build(); //Utilizamos la respuesta no content cuando acabamos de borrar algo
    }

    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll(){

        log.debug("REST Request for delete all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
