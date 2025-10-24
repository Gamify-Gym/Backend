package org.gamify.gym.app.training.dto;

import java.sql.Date;
import java.sql.Time;

public class CreateExerciseLogDto {
    private Double weight;
    private int reps;
    private Time timeIn;
    private Date dayMade;
    private Long playerId;
    private Long exerciseId;
    private String exerciseName;

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

    public Time getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Time timeIn) {
        this.timeIn = timeIn;
    }

    public Date getDayMade() {
        return dayMade;
    }

    public void setDayMade(Date dayMade) {
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

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
}