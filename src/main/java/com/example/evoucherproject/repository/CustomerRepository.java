package com.example.evoucherproject.repository;

import com.example.evoucherproject.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByPhone(String phone); // phone : 544567657456
}
