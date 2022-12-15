package com.buffsovernexus.balancesheet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Boolean> getApplicationHealth() {
        return ResponseEntity.ok(true);
    }
}
