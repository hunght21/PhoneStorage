package com.ltnc.phonestorage.service;

import com.ltnc.phonestorage.entity.Product;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    List<Product> getAllProducts();

    Product saveProduct(Product product,MultipartFile file);

    Product getProductById(Long partNumberId);

    Product updateProduct (Product product,MultipartFile file);

    void deleteProductById(Long partNumberId);
}
