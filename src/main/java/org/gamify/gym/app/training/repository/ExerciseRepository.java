package org.gamify.gym.app.training.repository;

import java.util.List;
import java.util.Optional;

import org.gamify.gym.app.training.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Query("SELECT e FROM Exercise e JOIN FETCH e.exerciseLogs WHERE e.id IN :exerciseIds")
    List<Exercise> findByIdsWithLogs(@Param("exerciseIds") List<Long> exerciseIds);

    @Query("SELECT e FROM Exercise e where e.name = :name AND e.player.user.email = :email")
    Optional<Exercise> findExerciseByNameAndEmail(@Param("name") String name, @Param("email") String email);
}