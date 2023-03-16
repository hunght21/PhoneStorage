package com.ltnc.phonestorage.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.ltnc.phonestorage.entity.Product;
import com.ltnc.phonestorage.repository.ProductRepository;
import com.ltnc.phonestorage.service.ProductService;


import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct( Product product,MultipartFile file) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long partNumberId) {
        return productRepository.findById(partNumberId).get();
    }

    @Override
    public Product updateProduct(Product product,MultipartFile file) {

        return productRepository.save(product);
    }

    // veliau ideti validacijas kad patikrint ar yra produktas pries ji trinant
    @Override
    public void deleteProductById(Long partNumberId) {
       productRepository.deleteById(partNumberId);

    }
}
