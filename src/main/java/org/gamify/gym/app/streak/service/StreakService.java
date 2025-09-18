package org.gamify.gym.app.streak.service;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
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
        List<PlayerActivity> playerActivity = playerActivityRepository.findPlayerActivityByEmail(email);
        if (playerActivity.isEmpty()) {
            new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return playerActivity;
    }

    @Transactional
    public PlayerActivity insertDailyActivity(String email, Status status,
            Optional<String> workoutName, Optional<LocalDate> date) {
        Player player = playerRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No player with email: " + email));
        Optional<Workout> workout = workoutRepository.findWorkoutByNameAndPlayerEmail(workoutName.orElse(null), email);

        LocalDate today = date.orElse(LocalDate.now());
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int currentWeekOfYear = today.get(weekFields.weekOfYear());

        if (player.getLastWeekOfYear() != 0 && player.getLastWeekOfYear() != currentWeekOfYear) {

            if (player.getCurrentWeekTrainedDays() >= player.getWeeklyTargetDays()) {

                player.setWeeklyStreak(player.getWeeklyStreak() + 1);
            } else {

                player.setWeeklyStreak(0);
            }

            player.setCurrentWeekTrainedDays(0);
            player.setLastWeekOfYear(currentWeekOfYear);
        } else if (player.getLastWeekOfYear() == 0) {

            player.setLastWeekOfYear(currentWeekOfYear);
        }

        if (status == Status.OK) {
            player.setCurrentWeekTrainedDays(player.getCurrentWeekTrainedDays() + 1);
        }

        playerRepository.save(player);

        PlayerActivity playerActivity = new PlayerActivity();
        playerActivity.setPlayer(player);
        playerActivity.setActiveDate(today);
        playerActivity.setStatus(status);
        playerActivity.setWorkout(workout.orElse(null));
        return playerActivityRepository.save(playerActivity);
    }

    @Transactional
    public Player setWeeklyTargetDays(String email, int targetDays) {
        Player player = playerRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No player with email: " + email));
        player.setWeeklyTargetDays(targetDays);
        return playerRepository.save(player);
    }

    @Transactional
    public Player getPlayerStreakInfo(String email) {
        return playerRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No player with email: " + email));
    }
}
