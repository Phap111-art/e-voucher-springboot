package com.example.evoucherproject.model.dto.request.account;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateAccountByUserDto { // have data
    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username is required")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;


}