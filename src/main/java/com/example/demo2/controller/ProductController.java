package com.example.demo2.controller;



import com.example.demo2.dto.ProductDTO;
import com.example.demo2.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        logger.info("Received request to create product: {}", productDTO);
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        logger.info("Received request to fetch all products");
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        logger.info("Received request to fetch product by ID: {}", id);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        logger.info("Received request to update product with ID: {}", id);
        return ResponseEntity.ok(productService.updateProduct(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        logger.info("Received request to delete product with ID: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
