package com.buffsovernexus.balancesheet.entity.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AccountResponse {

    private UUID uuid;
    private String username, email;
}
