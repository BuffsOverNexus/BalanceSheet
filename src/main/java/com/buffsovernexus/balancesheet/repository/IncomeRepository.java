package com.buffsovernexus.balancesheet.repository;

import com.buffsovernexus.balancesheet.entity.IncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IncomeRepository extends JpaRepository<IncomeEntity, UUID> {

}
