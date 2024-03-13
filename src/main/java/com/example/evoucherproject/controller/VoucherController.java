package com.example.evoucherproject.controller;

import com.example.evoucherproject.model.dto.CustomResponse;
import com.example.evoucherproject.service.PurchaseService;
import com.example.evoucherproject.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voucher/received")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;


    @GetMapping("/3_percent/{customerId}")
    public ResponseEntity<CustomResponse> claimVoucherWith3PercentDiscount(@PathVariable(name = "customerId") int customerId) {
        return voucherService.saveVoucher(customerId,3);
    }

    @GetMapping("/5_percent/{customerId}")
    public ResponseEntity<CustomResponse> claimVoucherWith5PercentDiscount(@PathVariable(name = "customerId") int customerId) {
        return voucherService.saveVoucher(customerId,5);
    }

    @GetMapping("/7_percent/{customerId}")
    public ResponseEntity<CustomResponse> claimVoucherWith7PercentDiscount(@PathVariable(name = "customerId") int customerId) {
        return voucherService.saveVoucher(customerId,7);
    }
}
