package org.gamify.gym.app.training.dto;

import java.util.List;

public class WorkoutDto {
    private String nome;
    private List<ExerciseDto> exercicio;
    private int totalExercicio;
    private int totalSeries;

    public WorkoutDto(String nome, List<ExerciseDto> exercicio, int totalExercicio, int totalSeries) {
        this.nome = nome;
        this.exercicio = exercicio;
        this.totalExercicio = totalExercicio;
        this.totalSeries = totalSeries;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ExerciseDto> getExercicio() {
        return exercicio;
    }

    public void setExercicio(List<ExerciseDto> exercicio) {
        this.exercicio = exercicio;
    }

    public int getTotalExercicio() {
        return totalExercicio;
    }

    public void setTotalExercicio(int totalExercicio) {
        this.totalExercicio = totalExercicio;
    }

    public int getTotalSeries() {
        return totalSeries;
    }

    public void setTotalSeries(int totalSeries) {
        this.totalSeries = totalSeries;
    }
}