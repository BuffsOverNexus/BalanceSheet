package com.buffsovernexus.balancesheet.entity.request;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private String username, password, email;
}
