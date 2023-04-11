package com.company.laptop_rest.repository;

import com.company.laptop_rest.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {

}
