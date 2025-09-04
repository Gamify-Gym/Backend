package org.gamify.gym.app.user.controller;

import org.gamify.gym.app.user.service.AuthService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gamify.gym.app.user.dto.LoginRequest;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Login {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Login(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public String login(@RequestBody String entity) throws Exception {
        try {
            LoginRequest loginRequest = objectMapper.readValue(entity, LoginRequest.class);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password));
            String token = authService.authenticate(authentication);
            return "{\"token\": \"" + token + "\"}";
        } catch (Exception e) {
            String errorMsg = e.getMessage() != null ? e.getMessage() : e.toString();
            return "{\"error\": \"Erro ao fazer login: " + errorMsg.replace("\"", "'") + "\"}";
        }
    }

    @GetMapping(value = "/check", produces = "application/json")
    public String check() {
        return "{\"valid\": \"true\"}";
    }

}
