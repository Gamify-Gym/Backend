package org.gamify.gym.app.training.repository;

import java.util.List;

import org.gamify.gym.app.training.model.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {
    List<ExerciseLog> findByExercise_IdExercise(Long exerciseId);
}
