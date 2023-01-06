package com.buffsovernexus.balancesheet.controller;

import com.buffsovernexus.balancesheet.entity.request.CreateAccountRequest;
import com.buffsovernexus.balancesheet.entity.response.AccountResponse;
import com.buffsovernexus.balancesheet.entity.response.AuthenticationResponse;
import com.buffsovernexus.balancesheet.service.AccountService;
import com.buffsovernexus.balancesheet.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private AccountService accountService;
    private AuthenticationService authenticationService;

    @GetMapping("/username")
    public ResponseEntity<AccountResponse> getAccount(@Param("username") String username) {
        return accountService.getAccountByUsername(username);
    }

    @GetMapping("/home")
    public ResponseEntity<AccountResponse> getAccount(Principal principal){
        return accountService.getAccountByUsername(principal.getName());
    }

    @PostMapping("/create")
    public ResponseEntity<AuthenticationResponse> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createAccount(createAccountRequest);
    }
}
