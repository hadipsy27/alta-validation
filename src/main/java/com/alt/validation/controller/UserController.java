package com.alt.validation.controller;

import com.alt.validation.entity.User;
import com.alt.validation.repository.UserRepository;
import com.alt.validation.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ValidationService validationService;

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping
    public Object createUser(@RequestBody User u){
        try {
            validationService.validate(u);
            User user = userRepository.save(u);
            return user;
        } catch (Exception e){
            return e.getMessage();
        }
    }
}
