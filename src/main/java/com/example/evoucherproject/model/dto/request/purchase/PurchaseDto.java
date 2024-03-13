package com.example.evoucherproject.model.dto.request.purchase;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
    @NotNull(message = "id customer is required")
    private int customerId;

    @NotNull(message = "id product is required")
    private int productId;

}
