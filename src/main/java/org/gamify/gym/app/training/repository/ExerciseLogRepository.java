package org.gamify.gym.app.training.repository;

import java.util.List;

import org.gamify.gym.app.training.model.ExerciseLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {
    @Query("SELECT l FROM ExerciseLog l WHERE l.player.user.email = :email AND l.exercise.id = :exerciseId")
    List<ExerciseLog> findAllExerciseLogs(
    @Param("email") String email,
    @Param("exerciseId") Long exerciseId
    );
}
