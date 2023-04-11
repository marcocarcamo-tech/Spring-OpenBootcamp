package com.company.laptop_rest.controller;

import com.company.laptop_rest.entity.Laptop;
import com.company.laptop_rest.repository.LaptopRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LaptopController {

    // Atributos

    LaptopRepository laptopRepository;
    //Constructor

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }



    // Recuperar todas las laptops de la base de datos
    @GetMapping("/api/laptops")
    private List<Laptop> getAllLaptops(){
        return laptopRepository.findAll();
    }

    //Inserar una laptop
    @PostMapping("/api/laptops")
    public Laptop createLaptop (@RequestBody Laptop laptop, @RequestHeader HttpHeaders header){

        //Imprimir información del tipo de contenido
        System.out.println(header.get("Content-Type"));

        //Guardar la laptop en la base de datos
        return laptopRepository.save(laptop);
    }


    //Encontrar por id

    //Crear

    //Actualizar

    //Borrar

    //Borrar todos
}
