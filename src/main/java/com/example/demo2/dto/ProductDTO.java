package com.example.demo2.dto;

public class ProductDTO {
    private Long id;
    private String name;
    private Double price; // Use Double, not double

    public ProductDTO(Long id, String name, Double price) {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
