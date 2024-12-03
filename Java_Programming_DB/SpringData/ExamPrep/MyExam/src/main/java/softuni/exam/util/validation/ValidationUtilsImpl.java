package softuni.exam.util.validation;


import jakarta.validation.Validation;
import jakarta.validation.Validator;


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