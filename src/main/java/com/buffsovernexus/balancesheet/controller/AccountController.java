package com.buffsovernexus.balancesheet.controller;

import com.buffsovernexus.balancesheet.entity.request.AuthenticationRequest;
import com.buffsovernexus.balancesheet.entity.request.CreateAccountRequest;
import com.buffsovernexus.balancesheet.entity.response.AccountResponse;
import com.buffsovernexus.balancesheet.entity.response.AuthenticationResponse;
import com.buffsovernexus.balancesheet.service.AccountService;
import com.buffsovernexus.balancesheet.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/username")
    public ResponseEntity<AccountResponse> getAccount(@Param("username") String username) {
        return accountService.getAccountByUsername(username);
    }

    @GetMapping("/home") //does same thing as /username route
    public ResponseEntity<AccountResponse> getAccount(Principal principal){
        return accountService.getAccountByUsername(principal.getName());
    }

    @PostMapping("/create")
    public ResponseEntity<AuthenticationResponse> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createAccount(createAccountRequest);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request).getBody());
    }

}
