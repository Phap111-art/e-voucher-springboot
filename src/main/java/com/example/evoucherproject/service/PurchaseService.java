package com.example.evoucherproject.service;


import java.util.List;

public interface PurchaseService {
    List<String> paymentCustomer(int customerId , int productId);
}
