package com.example.evoucherproject.controller;

import com.example.evoucherproject.model.dto.CustomResponse;
import com.example.evoucherproject.model.dto.request.account.LoginUserDto;
import com.example.evoucherproject.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsService detailsService;
    @Autowired
    private AccountService accountService;

    @PostMapping("/login-jwt")
    public ResponseEntity<CustomResponse> authenticateUser(@RequestBody @Valid LoginUserDto dto , BindingResult result) {
        return accountService.validateUserAndGenerateToken(dto,result,detailsService);
    }
    @GetMapping("/admin")
    public ResponseEntity<CustomResponse> admin() {
        return ResponseEntity.ok(new CustomResponse("Welcome come admin", HttpStatus.OK.value(), ""));
    }
    @GetMapping("/user")
    public ResponseEntity<CustomResponse> user() {
        return ResponseEntity.ok(new CustomResponse("Welcome come USER", HttpStatus.OK.value(), ""));
    }

    @GetMapping("/403")
    public ResponseEntity<String> accessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
    }
    @GetMapping("/home")
    public ResponseEntity<CustomResponse> home(Authentication authentication ) {
        return accountService.getUserInfoAfterAuthentication(authentication);
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("/login");
    }

    @GetMapping("/hello")
    public ResponseEntity<CustomResponse> hello() {
        return ResponseEntity.ok(new CustomResponse("Welcome come", HttpStatus.OK.value(), ""));
    }
}
