package org.gamify.gym.app.user.controller;

import org.gamify.gym.app.user.dto.UserCreationRequest;
import org.gamify.gym.app.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUser {
    private final UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user/create", consumes = "application/json")
    public ResponseEntity<?> createUser(@RequestBody UserCreationRequest request) {
        try {
            var user = userService.createUser(request.getUsername(), request.getEmail(), request.getPassword());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar usuário: " + e.getMessage());
        }
    }
}
