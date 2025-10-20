package org.gamify.gym.app.training.controller;

import java.util.List;

import org.gamify.gym.app.training.dto.AlterExerciseDto;
import org.gamify.gym.app.training.dto.AlterWorkoutDto;
import org.gamify.gym.app.training.dto.CreateExerciseDto;
import org.gamify.gym.app.training.dto.CreateWorkoutDto;
import org.gamify.gym.app.training.dto.WorkoutResponseDto;
import org.gamify.gym.app.training.model.Exercise;
import org.gamify.gym.app.training.model.Workout;
import org.gamify.gym.app.training.service.TrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/training")
public class TrainingController {

    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping(value = "/exercise")
    public ResponseEntity<?> insertExercise(@RequestBody CreateExerciseDto dto, Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            Exercise exercise = trainingService.insertExercise(email, dto.getNameExercise(), dto.getMuscles(),
                    dto.getRepeticoes(), dto.getSeries(), dto.getWorkout_name());
            return ResponseEntity.status(HttpStatus.CREATED).body(exercise);
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
            return ResponseEntity.status(HttpStatus.CREATED).body(workout);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/workout")
    public ResponseEntity<?> deleteWorkout(Authentication authentication, String workoutName) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            trainingService.deleteWorkout(email, workoutName);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/exercise")
    public ResponseEntity<?> deleteExercise(Authentication authentication, String exerciseName) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            trainingService.deleteExercise(email, exerciseName);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @PatchMapping(value = "/workout")
    public ResponseEntity<?> alterWorkout(Authentication authentication, @RequestBody AlterWorkoutDto dto) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            Workout workout = trainingService.alterWorkout(email, dto.getOldWorkoutName(), dto.getNewName(),
                    dto.getDescription());
            return ResponseEntity.ok(workout);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @PatchMapping(value = "/exercise")
    public ResponseEntity<?> alterExercise(Authentication authentication, @RequestBody AlterExerciseDto dto) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            Exercise exercise = trainingService.alterExercise(email, dto.getOldExerciseName(), dto.getNewName(),
                    dto.getMuscles(), dto.getRepeticoes(), dto.getSeries());
            return ResponseEntity.ok(exercise);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping(value = "/workout")
    public ResponseEntity<?> getWorkouts(Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            List<WorkoutResponseDto> workouts = trainingService.getWorkout(email);
            return ResponseEntity.ok(workouts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

}
