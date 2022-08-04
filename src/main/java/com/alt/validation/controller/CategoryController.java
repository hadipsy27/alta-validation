package com.alt.validation.controller;

import com.alt.validation.entity.Category;
import com.alt.validation.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    @PostMapping()
    public Object addCategory(@RequestBody Category c){
        try {
            Category category = categoryRepository.save(c);
            return category;
        } catch (Exception e){
            return e.getMessage();
        }
    }
}
