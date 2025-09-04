package org.gamify.gym.app.training.controller;

import org.gamify.gym.app.training.dto.WorkoutDto;
import org.gamify.gym.app.training.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/exercise", consumes = "application/json", produces = "application/json")
public class DailyDataController {

    @Autowired
    private WorkoutService workoutService;

    @GetMapping
    public List<WorkoutDto> getAllWorkouts(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getClaimAsString("sub");

        return workoutService.getWorkoutsByUserEmail(email);
    }
}