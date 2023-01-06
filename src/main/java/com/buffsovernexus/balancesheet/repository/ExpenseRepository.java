package com.buffsovernexus.balancesheet.repository;

import com.buffsovernexus.balancesheet.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, UUID> {
    Optional<ExpenseEntity> getExpenseById(UUID id);
}
