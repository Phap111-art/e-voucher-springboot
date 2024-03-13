package com.example.evoucherproject.controller;

import com.example.evoucherproject.model.dto.CustomResponse;
import com.example.evoucherproject.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @GetMapping("/{customerId}/{productId}")
    public ResponseEntity<List<String>> paymentCustomer(@PathVariable(name = "customerId") Integer customerId, @PathVariable(name = "productId") Integer productId) {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.paymentCustomer(customerId,productId));
    }
}
