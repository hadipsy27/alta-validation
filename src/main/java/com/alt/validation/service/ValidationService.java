package com.alt.validation.service;

import org.springframework.stereotype.Service;

import javax.validation.*;
import java.util.Set;

@Service
public class ValidationService {
    private Validator validator;

    public ValidationService() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }

    public <T> void validate(T input){
        Set<ConstraintViolation<T>> violations = validator.validate(input);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}
