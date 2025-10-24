package org.gamify.gym.app.training.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.gamify.gym.app.training.dto.WorkoutResponseDto;
import org.gamify.gym.app.training.model.Exercise;
import org.gamify.gym.app.training.model.ExerciseLog;
import org.gamify.gym.app.training.model.Workout;
import org.gamify.gym.app.training.repository.ExerciseRepository;
import org.gamify.gym.app.training.repository.ExerciseLogRepository;
import org.gamify.gym.app.training.repository.WorkoutRepository;
import org.gamify.gym.app.user.model.Player;
import org.gamify.gym.app.user.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TrainingService {

        @Autowired
        private PlayerRepository playerRepository;

        @Autowired
        private WorkoutRepository workoutRepository;

        @Autowired
        private ExerciseRepository exerciseRepository;

        @Autowired
        private ExerciseLogRepository exerciseLogRepository;

        public TrainingService(PlayerRepository playerRepository, WorkoutRepository workoutRepository,
                        ExerciseRepository exerciseRepository) {
                this.playerRepository = playerRepository;
                this.workoutRepository = workoutRepository;
                this.exerciseRepository = exerciseRepository;
        }

        @Transactional
        public Exercise insertExercise(String email, String nameExercise, String muscles, Integer repeticoes,
                        Integer series, String workoutName) {
                Workout workout = workoutRepository.findWorkoutByNameAndPlayerEmail(workoutName, email)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "No workout found with name: " + workoutName + " for this player"));

                Exercise exercise = new Exercise();
                exercise.setName(nameExercise);
                exercise.setMuscles(muscles);
                exercise.setRepeticoes(repeticoes);
                exercise.setSeries(series);
                exercise.setWorkout(workout);

                if (!exercise.getWorkout().getPlayer().getUser().getEmail().equals(email)) {
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient permissions");
                }

                return exerciseRepository.save(exercise);
        }

        @Transactional
        public ExerciseLog insertExerciseLog(Double weight, int reps, String email,
                        String exerciseName, Time time_in, Date day_made ) {
                Player player = playerRepository.findByUserEmail(email)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                Exercise exercise = exerciseRepository.findExerciseByNameAndEmail(exerciseName, email)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

                ExerciseLog exerciseLog = new ExerciseLog();
                exerciseLog.setWeight(weight);
                exerciseLog.setReps(reps);
                exerciseLog.setPlayer(player);
                exerciseLog.setExercise(exercise);
                exerciseLog.setTimeIn(time_in);
                exerciseLog.setDayMade(day_made);

                return exerciseLogRepository.save(exerciseLog);
        }

        @Transactional
        public Workout insertWorkout(String email, String name, String description) {
                workoutRepository.findWorkoutByNameAndPlayerEmail(name, email).ifPresent(existing -> {
                        throw new ResponseStatusException(HttpStatus.CONFLICT,
                                        "Workout already exists with name: " + name);
                });

                Workout workout = new Workout();
                workout.setName(name);
                workout.setDescription(description);
                workout.setPlayer(playerRepository.findByUserEmail(email)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "No player with provided email")));
                return workoutRepository.save(workout);

        }

        @Transactional
        public void deleteExercise(String email, String exerciseName) {
                Exercise exercise = exerciseRepository.findExerciseByNameAndEmail(exerciseName, email)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "No exercise with provided name"));
                if (!exercise.getWorkout().getPlayer().getUser().getEmail().equals(email)) {
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient permissions");
                }
                exerciseRepository.delete(exercise);
        }

        @Transactional
        public void deleteWorkout(String email, String workoutName) {
                Workout workout = workoutRepository.findWorkoutByNameAndPlayerEmail(workoutName, email)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "No workout with provided name"));
                if (!workout.getPlayer().getUser().getEmail().equals(email)) {
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient permissions");

                }
                workoutRepository.delete(workout);
        }

        @Transactional
        public Workout alterWorkout(String email, String oldWorkoutName, String name, String description) {
                Workout workout = workoutRepository.findWorkoutByNameAndPlayerEmail(oldWorkoutName, email)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "No workout with provided name"));

                if (!workout.getPlayer().getUser().getEmail().equals(email)) {
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient permissions");
                }
                workout.setDescription(description);
                workout.setName(name);
                return workoutRepository.save(workout);

        }

        @Transactional
        public Exercise alterExercise(String email, String oldExerciseName, String nameExercise, String muscles,
                        Integer repeticoes,
                        Integer series) {

                Exercise exercise = exerciseRepository.findExerciseByNameAndEmail(oldExerciseName, email)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "No workout with provided name"));
                if (!exercise.getWorkout().getPlayer().getUser().getEmail().equals(email)) {
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient permissions");
                }
                exercise.setName(nameExercise);
                exercise.setMuscles(muscles);
                exercise.setRepeticoes(repeticoes);
                exercise.setSeries(series);
                return exerciseRepository.save(exercise);
        }

        public List<WorkoutResponseDto> getWorkout(String email) {
                List<Workout> workout = workoutRepository.findAllWorkouts(email);
                if (workout.isEmpty()) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "No workouts for user");
                }

                List<WorkoutResponseDto> dtos = new ArrayList<>();
                for (Workout w : workout) {
                        int totalExercises = w.getExercises().size();
                        int totalSeries = w.getExercises().stream()
                                        .mapToInt(Exercise::getSeries)
                                        .sum();
                        dtos.add(new WorkoutResponseDto(w.getName(), w.getDescription(), w.getExercises(),
                                        totalExercises,
                                        totalSeries));
                }
                return dtos;
        }

        public Exercise getExerciseById(String email, Long id) {
                Exercise exercise = exerciseRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

                if (!exercise.getWorkout().getPlayer().getUser().equals(email)) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
                }
                return exercise;
        }

}
