package com.buffsovernexus.balancesheet.entity.request;

import com.buffsovernexus.balancesheet.enums.ReoccurringType;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateIncomeRequest {

    private UUID account;
    private Double amount;
    private ReoccurringType reoccurringType;
    private String label, description;
}
