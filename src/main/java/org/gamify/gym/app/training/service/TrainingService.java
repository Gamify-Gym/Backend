package org.gamify.gym.app.training.service;

import org.gamify.gym.app.training.model.Workout;
import org.gamify.gym.app.training.dto.CreateExerciseDto;
import org.gamify.gym.app.training.dto.CreateWorkoutDto;
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

}