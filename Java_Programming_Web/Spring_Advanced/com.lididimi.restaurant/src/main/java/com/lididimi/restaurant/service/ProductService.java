package com.lididimi.restaurant.service;

import com.lididimi.restaurant.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ResponseEntity<String> addNewProduct(Map<String, String> requestMap);

   ResponseEntity<List<ProductWrapper>> getAllProducts();
}
