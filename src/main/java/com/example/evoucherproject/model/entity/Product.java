package com.example.evoucherproject.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table (name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(name = "prices")
    private float price;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

}


