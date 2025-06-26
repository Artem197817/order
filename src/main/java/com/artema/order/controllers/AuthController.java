package com.artema.order.controllers;

import com.artema.order.model.auth.AuthRequest;
import com.artema.order.model.auth.AuthResponse;
import com.artema.order.model.auth.TokenRefreshRequest;
import com.artema.order.model.auth.TokenRefreshResponse;
import com.artema.order.security.JwtService;
import com.artema.order.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.validateRefreshToken(requestRefreshToken)
                .map(username -> {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    String newAccessToken = jwtService.generateToken(userDetails);
                    return ResponseEntity.ok(new TokenRefreshResponse(newAccessToken));
                })
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body(new TokenRefreshResponse(null))); // или с сообщением об ошибке внутри TokenRefreshResponse
    }
}



