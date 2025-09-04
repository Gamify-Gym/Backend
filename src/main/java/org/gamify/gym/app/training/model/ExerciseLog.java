package org.gamify.gym.app.training.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
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

    private LocalTime time_in;

    private LocalDate day_made;

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

    @PrePersist
    protected void onCreate() {
        if (this.time_in == null) {
            this.time_in = LocalTime.now().withNano(0);
        }
        if (this.day_made == null) {
            this.day_made = LocalDate.now();
        }
    }
}