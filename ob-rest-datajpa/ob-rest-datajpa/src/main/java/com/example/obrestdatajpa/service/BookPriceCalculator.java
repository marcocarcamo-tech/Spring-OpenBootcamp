package com.example.obrestdatajpa.service;

import com.example.obrestdatajpa.entity.Book;

public class BookPriceCalculator {
    public double calculatePrice(Book book){
        double price = book.getPrice();

        if(book.getNumPages() > 300){
            price += 50;
        }
        //Envío
        price+=50;
        return price;
    }
}
