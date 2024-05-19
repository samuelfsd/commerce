package com.samuelfsd.br.commerce.services;

import com.samuelfsd.br.commerce.dtos.product.ProductRequestDTO;
import com.samuelfsd.br.commerce.dtos.product.ProductResponseDTO;
import com.samuelfsd.br.commerce.entities.Product;
import com.samuelfsd.br.commerce.repositories.ProductRepository;
import com.samuelfsd.br.commerce.util.ICRUDHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ICRUDHandler<ProductRequestDTO, ProductResponseDTO> {

    // TODO: complete all methods

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public List<ProductResponseDTO> getAll() {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public ProductResponseDTO getById(Long id) {
        Optional<Product> productOpt = productRepository.findById(id);

        if(productOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado um produto com este id");
        }

        Product product = productOpt.get();

        return mapper.map(product, ProductResponseDTO.class);
    }

    @Override
    public ProductResponseDTO create(ProductRequestDTO dto) {
        return null;
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
