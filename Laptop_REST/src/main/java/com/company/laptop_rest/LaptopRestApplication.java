package com.company.laptop_rest;

import com.company.laptop_rest.entity.Laptop;
import com.company.laptop_rest.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LaptopRestApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(LaptopRestApplication.class, args);

		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);

		// Crear laptops manualmente

		Laptop laptop_1 =  new Laptop(null, "Lenovo", "Yoga 7i", "Intel i7", "SSD", 22999.00d);

		Laptop laptop_2 =  new Laptop(null, "Apple", "MacBook Pro", "Intel i7", "SSD", 32499.00d);

		//Guardar en base de datos

		laptopRepository.save(laptop_1);
		laptopRepository.save(laptop_2);


	}

}
