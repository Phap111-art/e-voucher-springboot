package com.example.evoucherproject.controller;

import com.example.evoucherproject.model.dto.CustomResponse;
import com.example.evoucherproject.model.dto.request.customer.CreateCustomerDto;
import com.example.evoucherproject.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getCustomerById(@PathVariable(name = "id") Integer id) {
        return customerService.getByIdCustomer(id);
    }

    @PostMapping()
    public ResponseEntity<CustomResponse> createCustomer(@Valid @RequestBody CreateCustomerDto dto, BindingResult result) {
        return customerService.createEmployee(dto, result);
    }
}
