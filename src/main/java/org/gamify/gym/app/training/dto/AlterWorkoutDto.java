package org.gamify.gym.app.training.dto;

public class AlterWorkoutDto {
    private String oldWorkoutName;
    private String description;
    private String newName;

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getOldWorkoutName() {
        return oldWorkoutName;
    }

    public void setOldWorkoutName(String oldWorkoutName) {
        this.oldWorkoutName = oldWorkoutName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
