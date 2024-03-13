package com.example.evoucherproject.service;

import com.example.evoucherproject.model.dto.CustomResponse;
import com.example.evoucherproject.model.dto.request.account.CreateAccountByAdminDto;
import com.example.evoucherproject.model.dto.request.account.CreateAccountByUserDto;
import com.example.evoucherproject.model.dto.request.account.LoginUserDto;
import com.example.evoucherproject.model.entity.Account;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();

    ResponseEntity<CustomResponse> getAccountById(Long id) ;

    ResponseEntity<CustomResponse> createAccountByUser(CreateAccountByUserDto dto, BindingResult result);
    ResponseEntity<CustomResponse> createAccountByAdmin(CreateAccountByAdminDto dto, BindingResult result);


    ResponseEntity<CustomResponse> updateAccount(Long id, CreateAccountByUserDto dto, BindingResult result);

    ResponseEntity<CustomResponse> deleteAccount(Long id);

    ResponseEntity<CustomResponse> validateUserAndGenerateToken(LoginUserDto dto, BindingResult result, UserDetailsService detailsService);

    ResponseEntity<CustomResponse> getUserInfoAfterAuthentication(Authentication authentication);


}
