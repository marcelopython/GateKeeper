package com.gateKeeper.manager.validation;

import com.gateKeeper.manager.validation.validator.CPFOrCNPJValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Payload;

@Constraint(validatedBy = CPFOrCNPJValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CPFOrCNPJ {
    String message() default "Invalid document";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
