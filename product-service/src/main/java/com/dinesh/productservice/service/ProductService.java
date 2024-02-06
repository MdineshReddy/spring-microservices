package com.dinesh.productservice.service;

import com.dinesh.productservice.dto.ProductRequestDTO;
import com.dinesh.productservice.dto.ProductResponseDTO;
import com.dinesh.productservice.model.Product;
import com.dinesh.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductRequestDTO productRequestDTO) {
        Product product = Product.builder().name(productRequestDTO.getName()).description(productRequestDTO.getDescription()).price(productRequestDTO.getPrice()).build();
        productRepository.save(product);
        log.info("Product {} is Saved.", product.getId());
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(p ->
                        ProductResponseDTO.builder()
                                .id(p.getId())
                                .name(p.getName())
                                .description(p.getDescription())
                                .price(p.getPrice())
                                .build()
                )
                .toList();
    }
}
