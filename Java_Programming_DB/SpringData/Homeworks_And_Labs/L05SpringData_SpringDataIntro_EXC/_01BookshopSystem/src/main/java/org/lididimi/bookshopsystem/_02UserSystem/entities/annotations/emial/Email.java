package org.lididimi.bookshopsystem._02UserSystem.entities.annotations.emial;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.lididimi.bookshopsystem._02UserSystem.constants.Constants.EMAIL_REGEX;
import static org.lididimi.bookshopsystem._02UserSystem.constants.Constants.INVALID_EMAIL;

@Component
@Constraint(validatedBy = EmailValidator.class)
@Target({FIELD, METHOD})
@Retention(RUNTIME)
public @interface Email {

    String message() default INVALID_EMAIL;

    int minUsernameLength() default 1;

    int maxUsernameLength() default 50;

    int maxHostNameLength() default 50;

    String regex() default EMAIL_REGEX;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}