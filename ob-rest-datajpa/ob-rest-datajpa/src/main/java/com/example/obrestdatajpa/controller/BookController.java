package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entity.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    //Crear un nuevo libro en base de datos

    //Actualizar un libro existente en base de datos


}
