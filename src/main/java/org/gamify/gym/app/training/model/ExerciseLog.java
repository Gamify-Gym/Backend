package org.gamify.gym.app.training.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

import org.gamify.gym.app.user.model.Player;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "exercises_logs")
public class ExerciseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double weight;

    private int reps;

    private Time time_in;

    private Date day_made;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    @JsonBackReference
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exercise")
    private Exercise exercise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Time getTimeIn() {
        return time_in;
    }

    public void setTimeIn(Time time_in) {
        this.time_in = time_in;
    }

    public Date getDayMade() {
        return day_made;
    }

    public void setDayMade(Date day_made) {
        this.day_made = day_made;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}