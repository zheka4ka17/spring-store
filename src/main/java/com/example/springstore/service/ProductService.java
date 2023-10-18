package com.example.springstore.service;

import com.example.springstore.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();
    void addToUserBucket(Long productId, String username);
}
