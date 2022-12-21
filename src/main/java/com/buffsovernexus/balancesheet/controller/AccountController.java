package com.buffsovernexus.balancesheet.controller;

import com.buffsovernexus.balancesheet.entity.request.AccountRequest;
import com.buffsovernexus.balancesheet.entity.response.AccountResponse;
import com.buffsovernexus.balancesheet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/account/username")
    public ResponseEntity<AccountResponse> getAccount(@Param("username") String username) {
        return accountService.getAccountByUsername(username);
    }

    @PostMapping("/account/create")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest) {
        return accountService.createAccount(accountRequest);
    }

}
