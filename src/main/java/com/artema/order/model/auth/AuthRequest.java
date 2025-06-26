package com.artema.order.model.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    public String getUsername() {
        return username;
    }

    private String username;

    public String getPassword() {
        return password;
    }

    private String password;


}

