package com.buffsovernexus.balancesheet.entity;

import com.buffsovernexus.balancesheet.enums.ReoccurringType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name = "income")
public class IncomeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private UUID uuid;

    private Double amount;
    private ReoccurringType reoccurringType;
    private String label, description;

}
