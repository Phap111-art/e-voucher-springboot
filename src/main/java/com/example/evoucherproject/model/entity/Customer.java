package com.example.evoucherproject.model.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@Table (name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    @Column(unique = true, nullable = false)
    private String name;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date birthday;
    @Column(unique = true, nullable = false,length = 50)
    private String email;
    @Column(unique = true, nullable = false,length = 50)
    private String address;
    @Column(unique = true, nullable = false,length = 50)
    private String phone;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
