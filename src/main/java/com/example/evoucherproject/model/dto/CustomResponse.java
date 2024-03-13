package com.example.evoucherproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {
    private String mess;
    private int httpStatus;
    private Object data;
}