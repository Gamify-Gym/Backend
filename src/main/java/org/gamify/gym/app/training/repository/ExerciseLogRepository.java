package org.gamify.gym.app.training.repository;

import org.gamify.gym.app.training.model.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {
}
