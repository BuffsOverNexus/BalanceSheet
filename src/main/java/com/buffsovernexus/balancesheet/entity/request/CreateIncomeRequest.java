package com.buffsovernexus.balancesheet.entity.request;

import com.buffsovernexus.balancesheet.enums.ReoccurringType;
import lombok.Data;

@Data
public class CreateIncomeRequest {
    private Double amount;
    private ReoccurringType reoccurringType;
    private String label, description;
}
