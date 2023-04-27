package com.company.laptop_rest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //@Value("${app.message}")
    //String message;
    @GetMapping("/saludo")
    private String saludo () {
        return "Hola desde la clase HelloController() ";
    }

}
