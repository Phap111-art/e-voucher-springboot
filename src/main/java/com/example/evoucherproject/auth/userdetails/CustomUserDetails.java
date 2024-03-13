package com.example.evoucherproject.auth.userdetails;

import com.example.evoucherproject.model.entity.Account;
import com.example.evoucherproject.model.entity.Role;
import com.example.evoucherproject.model.entity.Account;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private Account account;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = account.getRoles();
        return roles.stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return account.isStatus();
    }
}
