package org.gamify.gym.app.training.controller;

import org.gamify.gym.app.training.dto.CreateWorkoutDto;
import org.gamify.gym.app.training.model.Workout;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkoutController {
    @PostMapping(value="/workout", produces = "application/json")
    public ResponseEntity<?> createWorkout @RequestBody(CreateWorkoutDto createWorkoutDto){
        Workout workout = new Workout();
        workout.setName(createWorkoutDto.getName());
        workout.setDescription(createWorkoutDto.getDescription());
        return
    }

}
