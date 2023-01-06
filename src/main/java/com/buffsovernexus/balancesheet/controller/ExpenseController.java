package com.buffsovernexus.balancesheet.controller;

import com.buffsovernexus.balancesheet.entity.ExpenseEntity;
import com.buffsovernexus.balancesheet.entity.request.CreateExpenseRequest;
import com.buffsovernexus.balancesheet.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/expense")
public class ExpenseController {
    private ExpenseService expenseService;

    @GetMapping("/")
    public ResponseEntity<List<ExpenseEntity>> getAllExpenseByAccount(Principal principal){
        return expenseService.getAllExpensesByAccount(principal.getName());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseEntity> getExpenseById(Principal principal, @PathVariable UUID id){
        return expenseService.getExpenseById(principal.getName(), id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createExpense(Principal principal, @RequestBody CreateExpenseRequest request){
        return  expenseService.createExpense(principal.getName(), request);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteExpenseById(Principal principal, @PathVariable UUID id){
        return expenseService.deleteExpenseById(principal.getName(), id);
    }
}
