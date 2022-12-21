package com.buffsovernexus.balancesheet.service;

import com.buffsovernexus.balancesheet.entity.AccountEntity;
import com.buffsovernexus.balancesheet.entity.request.AccountRequest;
import com.buffsovernexus.balancesheet.entity.response.AccountResponse;
import com.buffsovernexus.balancesheet.repository.AccountRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<AccountResponse> getAccountByUsername(String username) {
        Optional<AccountEntity> account = accountRepository.findAccountByUsername(username);
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


    public ResponseEntity<AccountResponse> createAccount(AccountRequest accountRequest) {
        // Determine if entity exists
        try {
            String hashedPassword = hashString(accountRequest.getPassword());
            Optional<AccountEntity> existingAccount = accountRepository
                    .findAccountByUsernameAndPasswordOrEmail(accountRequest.getUsername(), hashedPassword, accountRequest.getEmail());
            if (existingAccount.isEmpty()) {
                AccountEntity account = new AccountEntity();
                account.setUsername(accountRequest.getUsername());
                account.setPassword(hashedPassword);
                account.setEmail(accountRequest.getEmail());
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
