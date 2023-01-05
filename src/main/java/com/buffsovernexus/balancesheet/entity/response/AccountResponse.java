package com.buffsovernexus.balancesheet.entity.response;

import com.buffsovernexus.balancesheet.entity.IncomeEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class AccountResponse {

    private UUID uuid;
    private String username, email;
    private List<IncomeEntity> income;

}
