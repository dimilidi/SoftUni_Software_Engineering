package org.lididimi.bookshopsystem._02UserSystem.entities.annotations;

import jakarta.validation.ConstraintValidatorContext;

public class AnnotationsUtil {
    public static void setErrorMessage(final ConstraintValidatorContext context, final String errorMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
    }
}
