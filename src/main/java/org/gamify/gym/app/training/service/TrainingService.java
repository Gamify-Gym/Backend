package org.gamify.gym.app.training.service;

import org.gamify.gym.app.training.model.Exercise;
import org.gamify.gym.app.training.model.Workout;
import org.gamify.gym.app.training.repository.ExerciseRepository;
import org.gamify.gym.app.training.repository.WorkoutRepository;
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

        public TrainingService(PlayerRepository playerRepository, WorkoutRepository workoutRepository,
                        ExerciseRepository exerciseRepository) {
                this.playerRepository = playerRepository;
                this.workoutRepository = workoutRepository;
                this.exerciseRepository = exerciseRepository;
        }

        @Transactional
        public Exercise insertExercise(String email, String nameExercise, String muscles, Integer repeticoes,
                        Integer series, String workoutName) {
                Exercise exercise = new Exercise();
                exercise.setName(nameExercise);
                exercise.setMuscles(muscles);
                exercise.setRepeticoes(repeticoes);
                exercise.setSeries(series);
                exercise.setWorkout(workoutRepository.findWorkoutByNameAndPlayerEmail(workoutName, email)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "No workout found with name: " + workoutName + " for this player")));

                return exerciseRepository.save(exercise);
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

}