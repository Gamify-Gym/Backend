package org.gamify.gym.app.training.repository;

import java.util.List;
import org.gamify.gym.app.training.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Query("SELECT e FROM Exercise e JOIN FETCH e.exerciseLogs WHERE e.id IN :exerciseIds")
    List<Exercise> findByIdsWithLogs(@Param("exerciseIds") List<Long> exerciseIds);
}