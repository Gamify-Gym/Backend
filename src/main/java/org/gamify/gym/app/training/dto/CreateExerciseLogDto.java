package org.gamify.gym.app.training.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateExerciseLogDto {
    private Double weight;
    private int reps;
    private LocalTime timeIn;
    private LocalDate dayMade;
    private Long playerId;
    private Long exerciseId;

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalDate getDayMade() {
        return dayMade;
    }

    public void setDayMade(LocalDate dayMade) {
        this.dayMade = dayMade;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }
}