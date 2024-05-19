package com.samuelfsd.br.commerce.controllers;

import com.samuelfsd.br.commerce.common.ApiPrefix;
import com.samuelfsd.br.commerce.dtos.product.ProductResponseDTO;
import com.samuelfsd.br.commerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ApiPrefix.API_V1_PREFIX + "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findByid(@PathVariable Long id){
        return ResponseEntity.ok(productService.getById(id));
    }
}