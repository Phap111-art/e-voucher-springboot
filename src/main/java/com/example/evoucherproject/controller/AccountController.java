package com.example.evoucherproject.controller;


import com.example.evoucherproject.model.dto.CustomResponse;
import com.example.evoucherproject.model.dto.request.account.CreateAccountByUserDto;
import com.example.evoucherproject.model.entity.Account;
import com.example.evoucherproject.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok().body(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getAccountById(@PathVariable(name = "id") Long id) {
        return accountService.getAccountById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<CustomResponse> createAccount(@Valid @RequestBody CreateAccountByUserDto dto, BindingResult result) {
        return accountService.createAccountByUser(dto, result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateAccount(@PathVariable Long id, @Valid @RequestBody CreateAccountByUserDto dto, BindingResult result) {
        return accountService.updateAccount(id, dto, result);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse> deleteCustomer(@PathVariable Long id) {
        return accountService.deleteAccount(id);
    }
}