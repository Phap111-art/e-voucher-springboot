package com.example.evoucherproject.model.dto.request.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCustomerDto{
        @NotBlank(message = "Name is required")
        private String name;

        @NotBlank(message = "Phone is required")
        private String phone;

        @NotBlank(message = "Email is required")
        private String email;

        @NotBlank(message = "Address is required")
        private String address;
}