package com.ecole221.api.gateway.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
