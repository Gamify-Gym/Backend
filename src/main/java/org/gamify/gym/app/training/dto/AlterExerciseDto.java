package org.gamify.gym.app.training.dto;

public class AlterExerciseDto {
    private String oldExerciseName;
    private String newName;
    private String muscles;
    private int repeticoes;
    private int series;

    public String getOldExerciseName() {
        return oldExerciseName;
    }

    public void setOldExerciseName(String oldExerciseName) {
        this.oldExerciseName = oldExerciseName;
    }

    public String getMuscles() {
        return muscles;
    }

    public void setMuscles(String muscles) {
        this.muscles = muscles;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
