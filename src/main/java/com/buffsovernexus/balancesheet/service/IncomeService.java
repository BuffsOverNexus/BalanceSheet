package com.buffsovernexus.balancesheet.service;

import com.buffsovernexus.balancesheet.entity.AccountEntity;
import com.buffsovernexus.balancesheet.entity.IncomeEntity;
import com.buffsovernexus.balancesheet.entity.request.CreateIncomeRequest;
import com.buffsovernexus.balancesheet.repository.AccountRepository;
import com.buffsovernexus.balancesheet.repository.IncomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@AllArgsConstructor
public class IncomeService {

    private IncomeRepository incomeRepository;

    private AccountService accountService;

    private AccountRepository accountRepository;

    public ResponseEntity<List<IncomeEntity>> getAllIncomeByAccount(String name) {
        AccountEntity accountEntity = accountService.getAccountEntityByUsername(name);
        return ResponseEntity.ok(accountEntity.getIncome());
    }

    public ResponseEntity<IncomeEntity> createIncomeItem(String name, CreateIncomeRequest createIncomeRequest) {
        try {
            AccountEntity accountEntity = accountService.getAccountEntityByUsername(name);

            if ( Objects.isNull(accountEntity) )
                return ResponseEntity.badRequest().build();
            else {
                // Create income object
                IncomeEntity incomeEntity = new IncomeEntity();
                BeanUtils.copyProperties(createIncomeRequest, incomeEntity);
                incomeRepository.save(incomeEntity);
                if ( Objects.isNull(accountEntity.getIncome()) ) {
                    accountEntity.setIncome(Collections.singletonList(incomeEntity));
                } else {
                    accountEntity.getIncome().add(incomeEntity);
                }
                accountRepository.save(accountEntity);
                return ResponseEntity.ok(incomeEntity);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
