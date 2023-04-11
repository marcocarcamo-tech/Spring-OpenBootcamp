package com.company.laptop_rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/saludo")
    private String saludo () {
        return "Hola desde la clase HelloController()";
    }

}
