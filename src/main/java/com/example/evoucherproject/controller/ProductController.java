package com.example.evoucherproject.controller;

import com.example.evoucherproject.model.dto.CustomResponse;
import com.example.evoucherproject.model.dto.request.product.CreateProductDto;
import com.example.evoucherproject.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController{
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getProductById(@PathVariable(name = "id") Integer id) {
        return productService.getbyidProduct(id);
    }

    @PostMapping()
    public ResponseEntity<CustomResponse> createProduct(@Valid @RequestBody CreateProductDto dto, BindingResult result) {
        return productService.createProduct(dto, result);
    }
}

