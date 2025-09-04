package org.gamify.gym.app.training.dto;

import org.gamify.gym.app.training.model.Exercise;
import java.util.List;

public class CreateWorkoutDto {
    private String name;
    private String description;
    private List<Exercise> exercises;

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
}
