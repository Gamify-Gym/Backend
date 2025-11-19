package org.gamify.gym.app.streak.repository;

import java.util.List;

import org.gamify.gym.app.streak.model.PlayerActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerActivityRepository extends JpaRepository<PlayerActivity, Long> {
    @Query("SELECT pa FROM PlayerActivity pa WHERE pa.player.user.email = :email")
    List<PlayerActivity> findPlayerActivityByEmail(String email);
}
