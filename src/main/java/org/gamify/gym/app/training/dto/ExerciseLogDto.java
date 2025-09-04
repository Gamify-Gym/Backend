package org.gamify.gym.app.training.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExerciseLogDto {
    private Double weight;
    private int reps;
    private LocalTime time_in;
    private LocalDate day_made;

    public ExerciseLogDto(Double weight, int reps, LocalTime time_in, LocalDate day_made) {
        this.weight = weight;
        this.reps = reps;
        this.time_in = time_in;
        this.day_made = day_made;
    }

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

    public LocalTime getTime_in() {
        return time_in;
    }

    public void setTime_in(LocalTime time_in) {
        this.time_in = time_in;
    }

    public LocalDate getDay_made() {
        return day_made;
    }

    public void setDay_made(LocalDate day_made) {
        this.day_made = day_made;
    }
}