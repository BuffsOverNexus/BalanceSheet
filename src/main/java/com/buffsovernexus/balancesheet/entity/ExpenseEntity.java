package com.buffsovernexus.balancesheet.entity;


import com.buffsovernexus.balancesheet.enums.ExpenseCategory;
import com.buffsovernexus.balancesheet.enums.ReoccurringType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "expense", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private UUID id;
    private Date date;
    private String label, description;
    private Double amount;
    private ExpenseCategory category;
    private ReoccurringType reoccurringType;



}
