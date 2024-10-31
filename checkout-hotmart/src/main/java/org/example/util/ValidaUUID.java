package org.example.util;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Documented
@Constraint(validatedBy = {})
@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$",
        flags = Pattern.Flag.CASE_INSENSITIVE)
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, PARAMETER})
public @interface ValidaUUID {

    String message() default "{UUID Invalido}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
