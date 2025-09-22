package org.gamify.gym.app.friendship.controller;

import org.gamify.gym.app.friendship.model.Friendship;
import org.gamify.gym.app.friendship.service.FriendshipService;
import org.gamify.gym.app.friendship.dto.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/friendship")
public class FriendshipController {

    private final FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @PostMapping
    public ResponseEntity<?> createFriendship(@RequestBody CreateFriendshipRequest request,
            Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            Friendship friendship = friendshipService.newFriendship(email, request.friendId());
            return ResponseEntity.ok(friendship);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/status")
    public ResponseEntity<?> changeStatus(@RequestBody ChangeStatusRequest request,
            Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            Friendship friendship = friendshipService.changeFriendshipStatus(
                    request.code(), email, request.friendId(), request.status());
            return ResponseEntity.ok(friendship);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFriendship(@RequestBody DeleteFriendshipRequest request,
            Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            friendshipService.deleteFriendship(request.code(), email, request.friendId());
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllFriendships(Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            List<Friendship> friendships = friendshipService.getAllFriendships(email);
            return ResponseEntity.ok(friendships);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}
