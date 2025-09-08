package org.gamify.gym.app.training.controller;

import org.gamify.gym.app.training.dto.CreateExerciseDto;
import org.gamify.gym.app.training.dto.CreateWorkoutDto;
import org.gamify.gym.app.training.model.Exercise;
import org.gamify.gym.app.training.model.Workout;
import org.gamify.gym.app.training.repository.ExerciseRepository;
import org.gamify.gym.app.training.service.TrainingService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController()
@RequestMapping(value = "/training")
public class TrainingController {

    private final ExerciseRepository exerciseRepository;

    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService, ExerciseRepository exerciseRepository) {
        this.trainingService = trainingService;
        this.exerciseRepository = exerciseRepository;
    }

    @PostMapping(value = "/exercise")
    public ResponseEntity<?> insertExercise(@RequestBody CreateExerciseDto dto, Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            Exercise exercise = trainingService.insertExercise(email, dto.getNameExercise(), dto.getMuscles(),
                    dto.getRepeticoes(), dto.getSeries(), dto.getWorkout_name());
            return ResponseEntity.ok(exercise);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @PostMapping(value = "/workout")
    public ResponseEntity<?> insertWorkout(@RequestBody CreateWorkoutDto dto, Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            Workout workout = trainingService.insertWorkout(email, dto.getName(), dto.getDescription());
            return ResponseEntity.ok(workout);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}
