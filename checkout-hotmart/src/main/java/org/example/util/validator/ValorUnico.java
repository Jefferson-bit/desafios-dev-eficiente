package org.example.util.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = {ValorUnicoValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValorUnico {

    String message() default "{jefferson.beanvalidator.uniquevalue}";

    Class<?>[] groups() default {}; // validação em grupo, validação fase 1 e fase 2

    Class<? extends Payload>[] payload() default {};

    String campo();

    Class<?> tabela();

}