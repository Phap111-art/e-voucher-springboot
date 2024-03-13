package com.example.evoucherproject.ultil;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationUtils {
    public static String getValidationErrorString(BindingResult bindingResult) {
        List<String> errorMessages = bindingResult.getFieldErrors()
                .stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return String.join(", ", errorMessages);
    }
}