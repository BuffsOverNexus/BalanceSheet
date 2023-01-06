package com.buffsovernexus.balancesheet.service;

import com.buffsovernexus.balancesheet.entity.AccountEntity;
import com.buffsovernexus.balancesheet.entity.ExpenseEntity;
import com.buffsovernexus.balancesheet.entity.request.CreateExpenseRequest;
import com.buffsovernexus.balancesheet.repository.AccountRepository;
import com.buffsovernexus.balancesheet.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private AccountService accountService;
    private AccountRepository accountRepository;
    private final Integer validDescriptionLength = 150;
    private final Integer validLabelLength = 50;

    public ResponseEntity<List<ExpenseEntity>> getAllExpensesByAccount(String username){
        AccountEntity user = accountService.getAccountEntityByUsername(username);
        return ResponseEntity.ok(user.getExpenses());
    }

    public ResponseEntity<ExpenseEntity> getExpenseById(String username, UUID id){
        ExpenseEntity expense = verifyOwnership(username, id);
        if (expense != null){
            return ResponseEntity.ok(expense);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<String> deleteExpenseById(String username, UUID id){
        ExpenseEntity myExpense = verifyOwnership(username, id); //verifies ownership before responding
        if (myExpense != null){
            AccountEntity user = accountService.getAccountEntityByUsername(username);
            user.getExpenses().remove(myExpense);
            accountRepository.save(user);
            return ResponseEntity.ok("Expense deleted.");
        } else {
            return ResponseEntity.ok("Account does not have expense to delete");
        }
    }

    public ResponseEntity<String> createExpense(String username,
                                                CreateExpenseRequest request
    ){
        try {
            //Make sure not some crazy length thats gonna make inconsistent rendering
            //on the front end
            boolean validRequest = (request.getLabel().length() < validLabelLength
                    && request.getDescription().length() < validDescriptionLength);
            if (!validRequest) return ResponseEntity.badRequest().build();

            AccountEntity user = accountService.getAccountEntityByUsername(username);
            ExpenseEntity newExpense = ExpenseEntity.builder()
                    .date(request.getDate())
                    .label(request.getLabel())
                    .description(request.getDescription())
                    .amount(request.getAmount())
                    .category(request.getCategory())
                    .reoccurringType(request.getReoccurringType())
                    .build();
            expenseRepository.save(newExpense);
            user.getExpenses().add(newExpense);
            accountRepository.save(user);
            return ResponseEntity.ok("Expense Successfully Created");
        } catch (Exception err){
            return ResponseEntity.internalServerError().build();
        }
    }

    public ExpenseEntity verifyOwnership(String username, UUID id){
        AccountEntity user = accountService.getAccountEntityByUsername(username);
        ExpenseEntity myExpense = user.getExpenses().stream()
                .filter(expense -> id.equals(expense.getId()))
                .findFirst()
                .orElse(null);
        if (myExpense == null){
            return null;
        }
        return myExpense;
    }
}
