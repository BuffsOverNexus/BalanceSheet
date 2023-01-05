package com.buffsovernexus.balancesheet.controller;

import com.buffsovernexus.balancesheet.entity.AccountEntity;
import com.buffsovernexus.balancesheet.entity.response.AccountResponse;
import com.buffsovernexus.balancesheet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/testbs")
public class authTestController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<String> testBs(
        Principal principal // "Principal principal" will bring back object with identity of currently authenticated user
    ){
        AccountEntity user = accountService.getAccountEntityByUsername(principal.getName()); // this is how to get account entity to work with
        return ResponseEntity.ok("Hello " + user.getIncome() + user.getUsername());
    }
}
