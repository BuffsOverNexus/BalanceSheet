package com.buffsovernexus.balancesheet.service;

import com.buffsovernexus.balancesheet.entity.AccountEntity;
import com.buffsovernexus.balancesheet.entity.request.CreateAccountRequest;
import com.buffsovernexus.balancesheet.entity.response.AccountResponse;
import com.buffsovernexus.balancesheet.entity.response.AuthenticationResponse;
import com.buffsovernexus.balancesheet.repository.AccountRepository;
import com.google.common.hash.Hashing;
import io.jsonwebtoken.Jwt;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public ResponseEntity<AccountResponse> getAccountByUsername(String username) {
        Optional<AccountEntity> account = accountRepository.findAccountByUsername(username);
        if (account.isPresent()) {
            AccountEntity accountEntity = account.get();
            AccountResponse response = AccountResponse.builder()
                    .email(accountEntity.getEmail())
                    .username(accountEntity.getUsername())
                    .uuid(accountEntity.getUuid())
                    .income(accountEntity.getIncome())
                    .build();
            return ResponseEntity.ok(response);
        }
        else
            return ResponseEntity.badRequest().build();
    }

    public AccountEntity getAccountEntityByUsername(String username){
        Optional<AccountEntity> account = accountRepository.findAccountByUsername(username);
        if (account.isPresent()){
            AccountEntity accountEntity = account.get();
            return accountEntity;
        }
        return null; // will never happen
    }


    public ResponseEntity<AuthenticationResponse> createAccount(CreateAccountRequest createAccountRequest) {
        // Determine if entity exists
        try {
            String encodedPassword = passwordEncoder.encode(createAccountRequest.getPassword());
            boolean unique = accountRepository.findAccountByUsername(createAccountRequest.getUsername()).isEmpty()
                    && accountRepository.findAccountByEmail(createAccountRequest.getEmail()).isEmpty();
            if (unique) {
                AccountEntity account = AccountEntity.builder()
                                .username(createAccountRequest.getUsername())
                                        .password(encodedPassword)
                                                .email(createAccountRequest.getEmail())
                                                        .build();
                accountRepository.save(account);
                var jwtToken = jwtService.generateToken(account);
                AuthenticationResponse response = AuthenticationResponse.builder().token(jwtToken).build();
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
