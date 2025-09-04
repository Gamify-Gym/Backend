package org.gamify.gym.app.streak.controller;

import java.util.List;

import org.gamify.gym.app.streak.dto.PlayerActivityDto;
import org.gamify.gym.app.streak.model.PlayerActivity;
import org.gamify.gym.app.streak.service.StreakService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
            PlayerActivity activity = streakService.insertDailyActivity(email, dto.getStatus(), dto.getWorkoutName());
            return ResponseEntity.ok(activity);
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
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

}
