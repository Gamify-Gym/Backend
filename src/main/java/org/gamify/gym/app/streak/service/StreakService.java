package org.gamify.gym.app.streak.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.gamify.gym.app.streak.model.PlayerActivity;
import org.gamify.gym.app.streak.model.PlayerActivity.Status;
import org.gamify.gym.app.streak.repository.PlayerActivityRepository;
import org.gamify.gym.app.training.model.Workout;
import org.gamify.gym.app.training.repository.WorkoutRepository;
import org.gamify.gym.app.user.model.Player;
import org.gamify.gym.app.user.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StreakService {
    @Autowired
    private PlayerActivityRepository playerActivityRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private WorkoutRepository workoutRepository;

    @Transactional
    public List<PlayerActivity> findDailyActivity(String email) {
        List<PlayerActivity> playerActivity = playerActivityRepository.findPlayerActivityByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No activity for player with email: " + email));
        return playerActivity;
    }

    @Transactional
    public PlayerActivity insertDailyActivity(String email, Status status,
            Optional<String> workoutName) {
        Player player = playerRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No player with email: " + email));
        Optional<Workout> workout = workoutRepository.findWorkoutByName(workoutName.orElse(null));
        PlayerActivity playerActivity = new PlayerActivity();
        playerActivity.setPlayer(player);
        playerActivity.setActiveDate(LocalDate.now());
        playerActivity.setStatus(status);
        playerActivity.setWorkout(workout.orElse(null));
        return playerActivityRepository.save(playerActivity);
    }
}
