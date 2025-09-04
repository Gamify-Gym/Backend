package org.gamify.gym.app.user.controller;

import org.springframework.web.bind.annotation.RestController;
import org.gamify.gym.app.user.dto.AssignTypeRequest;
import org.gamify.gym.app.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

@RestController
public class AssignType {
    private final UserService userService;

    public AssignType(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user/type", consumes = "application/json")
    public ResponseEntity<?> assignType(@AuthenticationPrincipal Jwt principal,
            @RequestBody AssignTypeRequest request) {
        try {
            var player = userService.assignType(principal.getClaimAsString("sub"), request.weight, request.height);
            return ResponseEntity.ok(player);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao dar um tipo ao usuario: " + e.getMessage());
        }
    }
}
