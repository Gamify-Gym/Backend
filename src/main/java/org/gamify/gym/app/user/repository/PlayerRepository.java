package org.gamify.gym.app.user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import org.gamify.gym.app.user.model.Player;
import org.gamify.gym.app.user.model.User;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByUser(User user);

    @Query("SELECT p FROM Player p WHERE p.user.email = :email")
    Optional<Player> findByUserEmail(String email);
}
