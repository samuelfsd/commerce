package com.samuelfsd.br.commerce.services;

import com.samuelfsd.br.commerce.dtos.product.ProductRequestDTO;
import com.samuelfsd.br.commerce.dtos.product.ProductResponseDTO;
import com.samuelfsd.br.commerce.entities.Product;
import com.samuelfsd.br.commerce.exceptions.DatabaseException;
import com.samuelfsd.br.commerce.exceptions.ResourceNotFoundException;
import com.samuelfsd.br.commerce.repositories.ProductRepository;
import com.samuelfsd.br.commerce.util.ICRUDHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService implements ICRUDHandler<ProductRequestDTO, ProductResponseDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;


    @Transactional(readOnly = true)
    @Override
    public Page<ProductResponseDTO> getAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);

        return products.map(product -> mapper.map(product, ProductResponseDTO.class));
    }

    @Transactional(readOnly = true)
    @Override
    public ProductResponseDTO getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não foi encotnrado um produto com este id"));

        return mapper.map(product, ProductResponseDTO.class);
    }

    @Transactional
    @Override
    public ProductResponseDTO create(ProductRequestDTO dto) {
        Product product = mapper.map(dto, Product.class);
        productRepository.save(product);

        return mapper.map(product, ProductResponseDTO.class);
    }

    @Transactional
    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado um produto com este id"));

        product = mapper.map(dto, Product.class);
        product.setId(dto.getId());

        product = productRepository.save(product);

        return mapper.map(product, ProductResponseDTO.class);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Não foi encontrado um produto com este id");
        }
      try {
          productRepository.deleteById(id);
      } catch (DataIntegrityViolationException e ) {
          throw new DatabaseException("Falha de integridade referencial.");
      }
    }
}
