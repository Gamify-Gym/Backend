package org.gamify.gym.app.training.dto;

import java.util.List;

public class ExerciseDto {
    private String nome;
    private int repeticoes;
    private int series;
    private List<ExerciseLogDto> logs;

    public ExerciseDto(String nome, int repeticoes, int series, List<ExerciseLogDto> logs) {
        this.nome = nome;
        this.repeticoes = repeticoes;
        this.series = series;
        this.logs = logs;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public List<ExerciseLogDto> getLogs() {
        return logs;
    }

    public void setLogs(List<ExerciseLogDto> logs) {
        this.logs = logs;
    }
}