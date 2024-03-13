package com.example.evoucherproject.service;

import com.example.evoucherproject.model.dto.CustomResponse;
import com.example.evoucherproject.model.dto.request.customer.CreateCustomerDto;
import com.example.evoucherproject.model.dto.request.product.CreateProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ProductService {
    ResponseEntity<CustomResponse> getbyidProduct(Integer id);
    ResponseEntity<CustomResponse> createProduct(CreateProductDto dto, BindingResult result);

}
