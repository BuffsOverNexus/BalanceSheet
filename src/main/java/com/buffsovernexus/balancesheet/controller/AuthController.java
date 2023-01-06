package com.buffsovernexus.balancesheet.controller;


import com.buffsovernexus.balancesheet.entity.request.AuthenticationRequest;
import com.buffsovernexus.balancesheet.entity.request.CreateAccountRequest;
import com.buffsovernexus.balancesheet.entity.response.AuthenticationResponse;
import com.buffsovernexus.balancesheet.service.AccountService;
import com.buffsovernexus.balancesheet.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private AccountService accountService;
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request).getBody());
    }
}
