package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entity.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    //Atributos

    private BookRepository bookRepository;

    //Constructores

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    //CRUD sobre la entidad Book

    //Buscar todos los libros (lista de libros)
    @GetMapping("/api/books")
    public List<Book> findAll() {
        //recuperar y devolver los libros de base de datos
        return bookRepository.findAll();
    }

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

    //Crear un nuevo libro en base de datos
    @PostMapping("/api/books")
    public Book create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        //Guardar el libro por parametro es la base de datos
        return bookRepository.save(book);
    }



    //Actualizar un libro existente en base de datos


}
