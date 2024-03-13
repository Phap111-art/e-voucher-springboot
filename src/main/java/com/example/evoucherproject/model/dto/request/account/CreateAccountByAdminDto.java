package com.example.evoucherproject.model.dto.request.account;

import com.example.evoucherproject.model.entity.Role;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateAccountByAdminDto { // have data
    @NotNull(message = "Username cannot be null")
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    private boolean status;
    @Transient
    private Set<Integer> roleId ;
    private Set<Role> roles;

}