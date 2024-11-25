package com.example.football.util.validation;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtilsImpl implements ValidationUtils {
    private final Validator validator;

    public ValidationUtilsImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).isEmpty();
    }
}