package org.gamify.gym.app.training.dto;

import java.util.List;

import org.gamify.gym.app.training.model.Exercise;

public class WorkoutResponseDto {

    private String name;
    private String description;
    private List<Exercise> exercises;
    private int totalExercises;
    private int totalSeries;

    public WorkoutResponseDto(String name, String description, List<Exercise> exercises, int totalExercises,
            int totalSeries) {
        this.name = name;
        this.description = description;
        this.exercises = exercises;
        this.totalExercises = totalExercises;
        this.totalSeries = totalSeries;
    }

    public int getTotalExercises() {
        return totalExercises;
    }

    public void setTotalExercises(int totalExercises) {
        this.totalExercises = totalExercises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public int getTotalSeries() {
        return totalSeries;
    }

    public void setTotalSeries(int totalSeries) {
        this.totalSeries = totalSeries;
    }

}
