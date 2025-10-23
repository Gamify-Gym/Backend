package org.gamify.gym.app.streak.controller;

import java.util.List;
import java.util.Optional;

import org.gamify.gym.app.streak.dto.PlayerActivityDto;
import org.gamify.gym.app.streak.model.PlayerActivity;
import org.gamify.gym.app.streak.service.StreakService;
import org.gamify.gym.app.user.model.Player;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class DailyWorkoutController {
    private StreakService streakService;

    public DailyWorkoutController(StreakService streakService) {
        this.streakService = streakService;
    }

    @PostMapping(value = "/workout/daily", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> dailyWorkout(@RequestBody PlayerActivityDto dto, Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            PlayerActivity activity = streakService.insertDailyActivity(email, dto.getStatus(), dto.getWorkoutName(),
                    Optional.ofNullable(dto.getToday()));
            return ResponseEntity.ok(activity);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body("Error: " + e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping(value = "/workout/daily", produces = "application/json")
    public ResponseEntity<?> getAllDailyWorkout(Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            List<PlayerActivity> activities = streakService.findDailyActivity(email);
            return ResponseEntity.ok(activities);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body("Error: " + e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @PutMapping(value = "/streak/target", produces = "application/json")
    public ResponseEntity<?> setWeeklyTarget(@RequestParam int targetDays, Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            Player player = streakService.setWeeklyTargetDays(email, targetDays);
            return ResponseEntity.ok(player);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body("Error: " + e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping(value = "/streak/info", produces = "application/json")
    public ResponseEntity<?> getStreakInfo(Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            Player player = streakService.getPlayerStreakInfo(email);
            return ResponseEntity.ok(player);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body("Error: " + e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

}
