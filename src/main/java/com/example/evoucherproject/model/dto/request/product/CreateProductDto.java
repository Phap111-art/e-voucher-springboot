package com.example.evoucherproject.model.dto.request.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be a positive value")
    private Float price;

    @NotNull(message = "Date is required")
    private Date date;
}
