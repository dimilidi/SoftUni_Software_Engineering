package org.lididimi.bookshopsystem._02UserSystem.entities.annotations.password;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Component
@Constraint(validatedBy = PasswordValidator.class)
@Target({FIELD, METHOD})
@Retention(RUNTIME)
public @interface Password {

    String message() default "Invalid password!";

    int minLength() default 6;

    int maxLength() default 30;

    boolean containsDigit() default true;

    boolean containsLowercase() default true;

    boolean containsUppercase() default true;

    boolean containsSpecialSymbol() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
