package com.samuelfsd.br.commerce.repositories;

import com.samuelfsd.br.commerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
