package org.gamify.gym.app.user.controller;

import org.gamify.gym.app.user.model.Player;
import org.gamify.gym.app.user.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GetOwnProfile {
    private PlayerRepository playerRepository;

    public GetOwnProfile(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping(value = "/user/profile")
    public ResponseEntity<?> getOwnProfile(Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");

            Player player = playerRepository.findByUserEmail(email)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            return ResponseEntity.ok(player);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
