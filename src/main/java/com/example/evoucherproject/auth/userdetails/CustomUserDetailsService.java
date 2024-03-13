package com.example.evoucherproject.auth.userdetails;

import com.example.evoucherproject.model.entity.Account;
import com.example.evoucherproject.exception.CustomException;
import com.example.evoucherproject.repository.AccountRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public class    CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Account> user = accountRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new CustomException("*Could not find username!", HttpStatus.UNAUTHORIZED);
        }
        return new CustomUserDetails(user.get());
    }
}
