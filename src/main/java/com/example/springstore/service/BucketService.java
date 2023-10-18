package com.example.springstore.service;

import com.example.springstore.dto.BucketDTO;
import com.example.springstore.models.Bucket;
import com.example.springstore.models.User;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productsIds);

    void addProducts(Bucket bucket, List<Long> productsIsd);

    BucketDTO getBucketByUser(String name);
}
