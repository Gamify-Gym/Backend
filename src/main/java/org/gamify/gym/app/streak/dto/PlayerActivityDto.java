package org.gamify.gym.app.streak.dto;

import java.time.LocalDate;
import java.util.Optional;

import org.gamify.gym.app.streak.model.PlayerActivity.Status;

public class PlayerActivityDto {
    private Optional<String> workoutName;
    private LocalDate today;
    private Status status;

    public Optional<String> getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(Optional<String> workoutName) {
        this.workoutName = workoutName;
    }

    public LocalDate getToday() {
        return today;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PlayerActivityDto(Optional<String> workoutName, LocalDate today, Status status) {
        this.workoutName = workoutName;
        this.today = today;
        this.status = status;
    }

}
