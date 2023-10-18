package com.example.springstore.service;

import com.example.springstore.dto.BucketDTO;
import com.example.springstore.dto.BucketDetailDTO;
import com.example.springstore.models.Bucket;
import com.example.springstore.models.Product;
import com.example.springstore.models.User;
import com.example.springstore.repositories.BucketRepository;
import com.example.springstore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService {
    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
@Autowired
    public BucketServiceImpl(BucketRepository bucketRepository, ProductRepository productRepository, UserService userService) {
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
    this.userService = userService;
}

    @Override
    public Bucket createBucket(User user, List<Long> productsIds) {
    Bucket bucket= new Bucket();
    bucket.setUser(user);
    List<Product> productList= getCollectRefProductsByIds(productsIds);
    bucket.setProducts(productList);
        return bucketRepository.save(bucket);
    }

    private List<Product> getCollectRefProductsByIds(List<Long> productsIds) {
    return productsIds.stream()
            .map(productRepository::getOne)//get one вытаскивает ссылку на объект, findById вытаскивает сам объект
            .collect(Collectors.toList());
    }

    @Override
    public void addProducts(Bucket bucket, List<Long> productsIsd) {
    List<Product> products= bucket.getProducts();
    List<Product> newProductList= products == null ? new ArrayList<>() : new ArrayList<>(products);
    bucket.setProducts(newProductList);
    bucketRepository.save(bucket);

    }

    @Override
    public BucketDTO getBucketByUser(String name) {
    User user= userService.findByName(name);
    if(user== null || user.getBucket()==null)
        return new BucketDTO();

    BucketDTO bucketDTO= new BucketDTO();
        Map<Long, BucketDetailDTO> mapByProductId= new HashMap<>();

        List<Product> products= user.getBucket().getProducts();
        for(Product product: products){
            BucketDetailDTO detail= mapByProductId.get(product.getId());
            if(detail== null){
                mapByProductId.put(product.getId(), new BucketDetailDTO(product));
            }
            else {
                detail.setAmount(detail.getAmount().add(new BigDecimal(1.0)));
                detail.setSum(detail.getSum()+ Double.valueOf(product.getPrice().toString()));
            }
        }
        bucketDTO.setBucketDetails(new ArrayList<>(mapByProductId.values()));
        bucketDTO.aggregate();
        return bucketDTO;
    }
}
