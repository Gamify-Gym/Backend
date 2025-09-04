package org.gamify.gym.app.training.service;

import org.gamify.gym.app.training.dto.WorkoutDto;
import org.gamify.gym.app.training.dto.ExerciseDto;
import org.gamify.gym.app.training.dto.ExerciseLogDto;
import org.gamify.gym.app.training.model.Workout;
import org.gamify.gym.app.training.model.Exercise;
import org.gamify.gym.app.training.repository.WorkoutRepository;
import org.gamify.gym.app.training.repository.ExerciseRepository;
import org.gamify.gym.app.user.model.Player;
import org.gamify.gym.app.user.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutService {

        @Autowired
        private PlayerRepository playerRepository;

        @Autowired
        private WorkoutRepository workoutRepository;

        @Autowired
        private ExerciseRepository exerciseRepository;

        @Transactional(readOnly = true)
        public List<WorkoutDto> getWorkoutsByUserEmail(String email) {
                Player player = playerRepository.findByUserEmail(email)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Player not found for user with email: " + email));

                // Fetch workouts with exercises
                List<Workout> workouts = workoutRepository.findByPlayerWithExercises(player);

                // Collect all exercise IDs
                List<Long> exerciseIds = workouts.stream()
                                .flatMap(workout -> workout.getExercises().stream())
                                .map(Exercise::getId)
                                .collect(Collectors.toList());

                // Fetch exercises with their logs
                List<Exercise> exercisesWithLogs = exerciseRepository.findByIdsWithLogs(exerciseIds);

                // Update exercises in workouts with their logs
                workouts.forEach(workout -> {
                        workout.getExercises().forEach(exercise -> {
                                exercisesWithLogs.stream()
                                                .filter(e -> e.getId().equals(exercise.getId()))
                                                .findFirst()
                                                .ifPresent(e -> exercise.setExerciseLogs(e.getExerciseLogs()));
                        });
                });

                // Transform to DTOs
                return workouts.stream().map(workout -> {
                        List<ExerciseDto> exerciseDtos = workout.getExercises().stream()
                                        .map(exercise -> {
                                                List<ExerciseLogDto> logDtos = exercise.getExerciseLogs().stream()
                                                                .map(log -> new ExerciseLogDto(
                                                                                log.getWeight(),
                                                                                log.getReps(),
                                                                                log.getTime_in(),
                                                                                log.getDay_made()))
                                                                .collect(Collectors.toList());
                                                return new ExerciseDto(
                                                                exercise.getName_exercise(),
                                                                exercise.getRepeticoes(),
                                                                exercise.getSeries(),
                                                                logDtos);
                                        })
                                        .collect(Collectors.toList());
                        int totalSeries = workout.getExercises().stream()
                                        .mapToInt(Exercise::getSeries)
                                        .sum();
                        return new WorkoutDto(
                                        workout.getName(),
                                        exerciseDtos,
                                        exerciseDtos.size(),
                                        totalSeries);
                }).collect(Collectors.toList());
        }
}