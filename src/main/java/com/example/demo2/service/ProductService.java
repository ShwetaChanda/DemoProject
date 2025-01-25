package com.example.demo2.service;


import com.example.demo2.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long id);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);
}