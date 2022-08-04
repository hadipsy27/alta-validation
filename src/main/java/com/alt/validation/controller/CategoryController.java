package com.alt.validation.controller;

import com.alt.validation.entity.Category;
import com.alt.validation.repository.CategoryRepository;
import com.mysql.cj.xdevapi.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/{id}")
    public Optional<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryPayload){
        Optional<Category> categoryById = categoryRepository.findById(id);
        // Lambda expression
        categoryById.ifPresent(res -> {
            res.setName(categoryPayload.getName());
            res.setDescription(categoryPayload.getDescription());

            categoryRepository.save(res);
        });
        return categoryById;
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id){
        Optional<Category> categoryById = categoryRepository.findById(id);
        // Lambda expression
        categoryById.ifPresent(res ->
                categoryRepository.delete(res)
        );

    }
}
