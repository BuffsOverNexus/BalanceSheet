package com.buffsovernexus.balancesheet.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private UUID uuid;
    private String username, password, email;

    @OneToMany
    private List<IncomeEntity> income;
}
