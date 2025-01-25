package com.example.demo2.service;

import com.example.demo2.dto.ProductDTO;
import com.example.demo2.entity.Product;
import com.example.demo2.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        logger.info("Creating product with details: {}", productDTO);

        // Ensure price is not null (Double can be null)
        if (productDTO.getPrice() == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }

        // Create a new Product entity from the ProductDTO
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        // Save the product to the repository
        product = productRepository.save(product);

        logger.info("Product created with ID: {}", product.getId());

        // Convert saved product to ProductDTO and return
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        logger.info("Fetching all products");

        // Fetch all products from the repository and convert to ProductDTO
        List<ProductDTO> products = productRepository.findAll()
                .stream()
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getPrice()))
                .collect(Collectors.toList());

        logger.info("Total products fetched: {}", products.size());
        return products;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        logger.info("Fetching product with ID: {}", id);

        // Find the product by ID or throw an exception if not found
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Product not found with ID: {}", id);
                    return new RuntimeException("Product not found");
                });

        logger.info("Product found: {}", product);
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        logger.info("Updating product with ID: {}", id);

        // Find the product by ID or throw an exception if not found
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Product not found with ID: {}", id);
                    return new RuntimeException("Product not found");
                });

        // Update the product's fields
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        // Save the updated product
        product = productRepository.save(product);

        logger.info("Product updated successfully with ID: {}", id);
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }

    @Override
    public void deleteProduct(Long id) {
        logger.info("Deleting product with ID: {}", id);

        // Check if the product exists
        if (!productRepository.existsById(id)) {
            logger.error("Product not found with ID: {}", id);
            throw new RuntimeException("Product not found");
        }

        // Delete the product
        productRepository.deleteById(id);
        logger.info("Product deleted successfully with ID: {}", id);
    }
}
