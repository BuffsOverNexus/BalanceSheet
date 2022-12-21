package com.buffsovernexus.balancesheet.service;

import com.buffsovernexus.balancesheet.entity.AccountEntity;
import com.buffsovernexus.balancesheet.entity.IncomeEntity;
import com.buffsovernexus.balancesheet.entity.request.CreateIncomeRequest;
import com.buffsovernexus.balancesheet.repository.AccountRepository;
import com.buffsovernexus.balancesheet.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<List<IncomeEntity>> getAllIncomeByAccount(UUID account) {
        AccountEntity accountEntity = accountRepository.getReferenceById(account);
        return ResponseEntity.ok(accountEntity.getIncome());
    }

    public ResponseEntity<IncomeEntity> createIncomeItem(CreateIncomeRequest createIncomeRequest) {
        try {
            AccountEntity accountEntity = accountRepository.getReferenceById(createIncomeRequest.getAccount());
            // Create income object
            IncomeEntity incomeEntity = new IncomeEntity();
            incomeEntity.setAmount(createIncomeRequest.getAmount());
            incomeEntity.setDescription(createIncomeRequest.getDescription());
            incomeEntity.setLabel(createIncomeRequest.getLabel());
            incomeEntity.setReoccurringType(createIncomeRequest.getReoccurringType());
            incomeRepository.save(incomeEntity);
            if (Objects.isNull(accountEntity.getIncome())) {
                accountEntity.setIncome(Collections.singletonList(incomeEntity));
            } else {
                accountEntity.getIncome().add(incomeEntity);
            }
            accountRepository.save(accountEntity);
            return ResponseEntity.ok(incomeEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
