package com.example.obrestdatajpa;

import com.example.obrestdatajpa.entity.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);

		BookRepository repository = context.getBean(BookRepository.class);

		//CRUD

		//Crear libro
		Book book1 = new Book(null, "El llano en llamas", "Juan Rulfo", 120, 112.99, LocalDate.of(1999, 05, 03), true);
		Book book2 = new Book(null, "El laberinto de la soledad", "Octavio Paz", 280, 149.99, LocalDate.of(1995, 07, 10), true);
		//Almacenar libro

		System.out.println("Numero de libros en BD: " + repository.findAll().size());

		repository.save(book1);
		repository.save(book2);
		//Recuperar todos los libros

		System.out.println("Numero de libros en BD: " + repository.findAll().size());

		//Borrar un libro
		//repository.delete(book1);

		//System.out.println("Numero de libros en BD: " + repository.findAll().size());
	}

}
