package com.example.evoucherproject.service;

import com.example.evoucherproject.model.dto.CustomResponse;
import com.example.evoucherproject.model.dto.request.customer.CreateCustomerDto;
import com.example.evoucherproject.model.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface CustomerService {
    ResponseEntity<CustomResponse> getByIdCustomer(Integer id);
    ResponseEntity<CustomResponse> createEmployee(CreateCustomerDto dto, BindingResult result);
}
