package com.example.evoucherproject.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "vouchers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voucherId;

    @Column(nullable = false)
    private Date startTime;

    @Column(nullable = false)
    private Date endTime;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int discount = 0;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    private Customer customer;
}

