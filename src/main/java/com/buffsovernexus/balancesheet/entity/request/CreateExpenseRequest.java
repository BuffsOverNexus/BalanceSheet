package com.buffsovernexus.balancesheet.entity.request;

import com.buffsovernexus.balancesheet.enums.ExpenseCategory;
import com.buffsovernexus.balancesheet.enums.ReoccurringType;
import lombok.Data;

import java.util.Date;

@Data
public class CreateExpenseRequest {
    private Date date;
    private String label, description;
    private Double amount;
    private ExpenseCategory category;
    private ReoccurringType reoccurringType;

    public CreateExpenseRequest(Date date, String label, String description,
                                Double amount, ExpenseCategory category,
                                ReoccurringType reoccurringType){
        if(this.date==null){
            this.date = new Date();
        } else {
            this.date = date;
        }

        this.label = label;
        this.description = description;
        this.amount = amount;

        if(this.category == null){
            this.category = ExpenseCategory.OTHER;
        } else {
            this.category = category;
        }

        if (this.reoccurringType == null){
            this.reoccurringType = ReoccurringType.ONCE;
        } else {
            this.reoccurringType = reoccurringType;
        }

    }
}
