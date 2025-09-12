package org.gamify.gym.app.training.repository;

import java.util.List;
import java.util.Optional;
import org.gamify.gym.app.training.model.Workout;
import org.gamify.gym.app.user.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    @Query("SELECT w FROM Workout w JOIN FETCH w.exercises WHERE w.player = :player")
    Optional<List<Workout>> findByPlayerWithExercises(@Param("player") Player player);

    @Query("SELECT w FROM Workout w WHERE w.name = :name AND w.player.user.email = :email")
    Optional<Workout> findWorkoutByNameAndPlayerEmail(@Param("name") String name, @Param("email") String email);

    @Query("SELECT w FROM Workout w WHERE w.player.user.email = :email")
    List<Workout> findAllWorkouts(@Param("email") String email);
}