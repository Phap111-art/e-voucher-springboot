package com.example.evoucherproject.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long accountId;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String password;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToOne(mappedBy = "account")
    private Customer Customer;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

}
