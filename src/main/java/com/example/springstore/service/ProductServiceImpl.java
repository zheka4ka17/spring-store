package com.example.springstore.service;

import com.example.springstore.dto.ProductDTO;
import com.example.springstore.mapper.ProductMapper;
import com.example.springstore.models.Bucket;
import com.example.springstore.models.User;
import com.example.springstore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductMapper mapper=ProductMapper.MAPPER;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final BucketService bucketService;
@Autowired

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, BucketService bucketService) {
        this.productRepository = productRepository;
    this.userService = userService;
    this.bucketService = bucketService;
}

    @Override
    public List<ProductDTO> findAll() {
        return mapper.toProductDTOList(productRepository.findAll());
    }

    @Override
    public void addToUserBucket(Long productId, String username) {
    User user= userService.findByName(username);
    if(user==null){
        throw new RuntimeException("User not found - "+ username);
    }

        Bucket bucket= user.getBucket();
    if(bucket== null){
        Bucket newBucket= bucketService.createBucket(user, Collections.singletonList(productId));
        user.setBucket(newBucket);
        userService.save(user);
    }
    else bucketService.addProducts(bucket, Collections.singletonList(productId));

    }
}
