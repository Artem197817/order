package com.artema.order.services;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Data
public class RefreshTokenService {
    private final Map<String, String> refreshTokens = new ConcurrentHashMap<>();

    public String createRefreshToken(String username) {
        String refreshToken = UUID.randomUUID().toString();
        refreshTokens.put(refreshToken, username);
        return refreshToken;
    }

    public Optional<String> validateRefreshToken(String token) {
        return Optional.ofNullable(refreshTokens.get(token));
    }

    public void revokeRefreshToken(String token) {
        refreshTokens.remove(token);
    }

}
