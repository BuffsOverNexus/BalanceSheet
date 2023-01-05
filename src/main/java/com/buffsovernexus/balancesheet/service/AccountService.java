package com.buffsovernexus.balancesheet.service;

import com.buffsovernexus.balancesheet.entity.AccountEntity;
import com.buffsovernexus.balancesheet.entity.request.CreateAccountRequest;
import com.buffsovernexus.balancesheet.entity.response.AccountResponse;
import com.buffsovernexus.balancesheet.repository.AccountRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<AccountResponse> getAccountByUsername(String username) {
        Optional<AccountEntity> account = accountRepository.findAccountByUsername(username.toLowerCase());
        if (account.isPresent()) {
            AccountEntity accountEntity = account.get();
            AccountResponse response = AccountResponse.builder()
                    .email(accountEntity.getEmail())
                    .username(accountEntity.getUsername())
                    .uuid(accountEntity.getUuid())
                    .build();
            return ResponseEntity.ok(response);
        }
        else
            return ResponseEntity.badRequest().build();
    }


    public ResponseEntity<AccountResponse> createAccount(CreateAccountRequest createAccountRequest) {
        // Determine if entity exists
        try {
            String hashedPassword = hashString(createAccountRequest.getPassword());
            Optional<AccountEntity> existingAccount = accountRepository
                    .findAccountByUsernameAndPasswordOrEmail(createAccountRequest.getUsername(), hashedPassword, createAccountRequest.getEmail());
            if (existingAccount.isEmpty()) {
                AccountEntity account = new AccountEntity();
                account.setUsername(createAccountRequest.getUsername());
                account.setPassword(hashedPassword);
                account.setEmail(createAccountRequest.getEmail());
                accountRepository.save(account);
                AccountResponse response = AccountResponse.builder().username(account.getUsername()).uuid(account.getUuid()).email(account.getEmail()).build();
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    private String hashString(String string) {
        return Hashing.sha256()
                .hashString(string, StandardCharsets.UTF_8)
                .toString();
    }
}
