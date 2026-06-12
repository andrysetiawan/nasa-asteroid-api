package com.andry.nasa_asteroid_api.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.andry.nasa_asteroid_api.validation.validator.AsteroidDateRangeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AsteroidDateRangeValidator.class)
public @interface AsteroidDateRange {
    String message() default "Date range must not exceed 7 days";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
