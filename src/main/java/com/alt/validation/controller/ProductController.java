package com.alt.validation.controller;

import com.alt.validation.entity.Product;
import com.alt.validation.repository.ProductRepository;
import com.alt.validation.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ValidationService validationService;

    @GetMapping
    public List<Product> getAllData(){
        return productRepository.findAll();
    }

    @PostMapping
    public Object createProduct(@RequestBody Product p){
        try {
            validationService.validate(p);
            Product product = productRepository.save(p);
            return product;
        } catch (Exception e){
            return e.getMessage();
        }
    }

}
