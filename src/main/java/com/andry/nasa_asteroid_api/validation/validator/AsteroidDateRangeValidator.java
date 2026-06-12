package com.andry.nasa_asteroid_api.validation.validator;

import java.time.temporal.ChronoUnit;

import com.andry.nasa_asteroid_api.dto.request.AsteroidQueryRequest;
import com.andry.nasa_asteroid_api.validation.annotation.AsteroidDateRange;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AsteroidDateRangeValidator implements ConstraintValidator<AsteroidDateRange, AsteroidQueryRequest> {

    @Override
    public boolean isValid(AsteroidQueryRequest value, ConstraintValidatorContext context) {
        if (value == null || value.getStartDate() == null || value.getEndDate() == null) {
            return true;
        }

        long daysBetween = ChronoUnit.DAYS.between(value.getStartDate(), value.getEndDate());
        return daysBetween >= 0 && daysBetween <= 7;
    }

}
