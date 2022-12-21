package com.buffsovernexus.balancesheet.service;

import com.buffsovernexus.balancesheet.entity.AccountEntity;
import com.buffsovernexus.balancesheet.entity.IncomeEntity;
import com.buffsovernexus.balancesheet.repository.AccountRepository;
import com.buffsovernexus.balancesheet.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

}
