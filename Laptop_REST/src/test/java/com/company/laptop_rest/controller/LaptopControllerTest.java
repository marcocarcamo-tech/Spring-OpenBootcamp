package com.company.laptop_rest.controller;

import com.company.laptop_rest.entity.Laptop;
import com.company.laptop_rest.repository.LaptopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }
    @Autowired
    LaptopRepository laptopRepository;

    @DisplayName("Comporbar la creación de una laptop")
    @Test
    void createLaptop() {
        //Configuración de la prueba

        //Definimos los headers de la petición
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        //Definimos el cuerpo de la petición
        String json = "{\n" +
                "\t\"brand\": \"brand test\",\n" +
                "\t\"model\": \"model test\",\n" +
                "\t\"processor\": \"processor test\",\n" +
                "\t\"memoryType\": \"memory test\",\n" +
                "\t\"price\": 2000.0\n" +
                "}";

        //Creamos una entidad http y le pasamos el cuerpo y headers
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        //Ejecución y hacer asserts
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("brand test", result.getBrand());
        assertEquals("model test", result.getModel());
        assertEquals("processor test", result.getProcessor());


    }

    @Test
    void findOneLaptopById() {
        //Configuración de la prueba

        //Definimos los headers de la petición
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


        Laptop laptop=  new Laptop(null, "Lenovo", "Yoga 7i", "Intel i7", "SSD", 22999.0);

        laptopRepository.save(laptop);
        //Definimos el cuerpo de la petición
        String json = "{}";

        //Creamos una entidad http y le pasamos el cuerpo y headers
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        //Ejecución y hacer asserts
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops/1", HttpMethod.GET, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("Lenovo", result.getBrand());

    }

    @Test
    void updateLaptop() {

    }

    @Test
    void deleteOneLaptopById() {
    }

    @Test
    void deleteAllLaptops() {
    }
}