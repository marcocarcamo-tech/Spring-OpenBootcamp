package com.company.laptop_rest.controller;

import com.company.laptop_rest.entity.Laptop;
import com.company.laptop_rest.repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    // Atributos
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

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

    //Encontrar por id
    @GetMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> findOneLaptopById (@PathVariable Long id){

        Optional<Laptop> laptopOpt = laptopRepository.findById(id);

        if(laptopOpt.isPresent()){
            return ResponseEntity.ok(laptopOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Crear una laptop
    @PostMapping("/api/laptops")
    public Laptop createLaptop (@RequestBody Laptop laptop, @RequestHeader HttpHeaders header){

        //Imprimir información del tipo de contenido
        System.out.println(header.get("Content-Type"));

        //Guardar la laptop en la base de datos
        return laptopRepository.save(laptop);
    }

    //Actualizar
    @PutMapping("/api/laptops")
    public ResponseEntity updateLaptop (@RequestBody Laptop laptop) {

        if(laptop.getId() == null){
            log.warn("Trying to update a new book with id");
            return ResponseEntity.badRequest().build();
        }
        if(!laptopRepository.existsById(laptop.getId())){
            log.warn("Trying to update a new book with non existing id");
            return ResponseEntity.notFound().build();
        }

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    //Borrar uno por id

    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> deleteOneLaptopById (@PathVariable  Long id){

        Optional<Laptop> laptopOpt = laptopRepository.findById(id);

        if (laptopOpt.isEmpty()) {
            log.warn("Trying to delete non existent Laptop object");
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    //Borrar todos

    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAllLaptops (){


        List<Laptop> laptops = laptopRepository.findAll();

        if (laptops.isEmpty()){
            log.warn("Trying to delete non existent Laptops");
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();

    }

}
