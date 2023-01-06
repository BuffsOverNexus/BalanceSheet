package com.buffsovernexus.balancesheet.controller;

import com.buffsovernexus.balancesheet.entity.IncomeEntity;
import com.buffsovernexus.balancesheet.entity.request.CreateIncomeRequest;
import com.buffsovernexus.balancesheet.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping("/")
    public ResponseEntity<List<IncomeEntity>> getAllIncomeByAccount(Principal principal) {
        return incomeService.getAllIncomeByAccount(principal.getName());
    }

    @PostMapping("/create")
    public ResponseEntity<IncomeEntity> createIncomeItem(Principal principal,
                                                         @RequestBody CreateIncomeRequest createIncomeRequest) {
        return incomeService.createIncomeItem(principal.getName(), createIncomeRequest);
    }

}
