package com.buffsovernexus.balancesheet.controller;

import com.buffsovernexus.balancesheet.entity.IncomeEntity;
import com.buffsovernexus.balancesheet.entity.request.CreateIncomeRequest;
import com.buffsovernexus.balancesheet.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping("/income")
    public ResponseEntity<List<IncomeEntity>> getAllIncomeByAccount(@Param("account") UUID account) {
        return incomeService.getAllIncomeByAccount(account);
    }

    @PostMapping("/income/create")
    public ResponseEntity<IncomeEntity> createIncomeItem(@RequestBody CreateIncomeRequest createIncomeRequest) {
        return incomeService.createIncomeItem(createIncomeRequest);
    }

}
