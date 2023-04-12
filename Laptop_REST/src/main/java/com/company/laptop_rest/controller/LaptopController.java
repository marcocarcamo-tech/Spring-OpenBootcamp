package com.company.laptop_rest.controller;

import com.company.laptop_rest.entity.Laptop;
import com.company.laptop_rest.repository.LaptopRepository;
import io.swagger.v3.oas.annotations.Operation;
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



    @Operation(summary = "Obtiene todas las laptops existentes")
    // Recuperar todas las laptops de la base de datos
    @GetMapping("/api/laptops")
    private List<Laptop> getAllLaptops(){
        return laptopRepository.findAll();
    }

    //Encontrar por id
    @Operation(summary = "Obtiene una laptop indicando su id en la url")
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
    @Operation(summary = "Crea una laptop indicando el cuerpo en la petición")
    @PostMapping("/api/laptops")
    public Laptop createLaptop (@RequestBody Laptop laptop, @RequestHeader HttpHeaders header){

        //Imprimir información del tipo de contenido
        System.out.println(header.get("Content-Type"));

        //Guardar la laptop en la base de datos
        return laptopRepository.save(laptop);
    }

    //Actualizar
    @Operation(summary = "Actualiza una laptop indicando el cuerpo en la petición")
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

    @Operation(summary = "Borra una laptop indicando su id en la url")
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

    @Operation(summary = "Borra todas las laptops")
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
