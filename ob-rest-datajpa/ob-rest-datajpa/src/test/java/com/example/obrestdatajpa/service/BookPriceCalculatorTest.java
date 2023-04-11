package com.example.obrestdatajpa.service;

import com.example.obrestdatajpa.entity.Book;
import org.hibernate.type.descriptor.java.LocalDateJavaDescriptor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculateBook() {

        //Configuración de la prueba
        Book book = new Book(null,"El principito", "Exupery", 400, 300.d, LocalDate.of(2000,11, 8), true);
        BookPriceCalculator calculator = new BookPriceCalculator();

        //Se ejecuta el comportamiento a testear
        double price = calculator.calculatePrice(book);
        System.out.println(price);

        //Comprobaciones asercionesd
        assertTrue(price>0);
        assertEquals(400.0, price);
    }
}