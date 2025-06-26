package com.artema.order.model.auth;

import lombok.Data;

@Data
public class TokenRefreshRequest {
    private String refreshToken;
}
