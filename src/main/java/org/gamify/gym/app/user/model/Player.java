package org.gamify.gym.app.user.model;

import java.util.List;

import org.gamify.gym.app.streak.model.PlayerActivity;
import org.gamify.gym.app.training.model.Workout;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_player;

    @Column
    private Double height;

    @Column
    private Double weight;

    @Column
    private Integer weeklyTargetDays = 2; 

    @Column
    private Integer weeklyStreak = 0;

    @Column
    private Integer lastWeekOfYear = 0;

    @Column
    private Integer currentWeekTrainedDays = 0;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Workout> workouts;

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "personal_trainer_id")
    private PersonalTrainer personalTrainer;

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public PersonalTrainer getPersonalTrainer() {
        return personalTrainer;
    }

    public void setPersonalTrainer(PersonalTrainer personalTrainer) {
        this.personalTrainer = personalTrainer;
    }

    public Nutritionist getNutritionist() {
        return nutritionist;
    }

    public void setNutritionist(Nutritionist nutritionist) {
        this.nutritionist = nutritionist;
    }

    @ManyToOne
    @JoinColumn(name = "nutritionist_id")
    private Nutritionist nutritionist;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PlayerActivity> activities;

    public Integer getWeeklyTargetDays() {
        return weeklyTargetDays;
    }

    public void setWeeklyTargetDays(Integer weeklyTargetDays) {
        this.weeklyTargetDays = weeklyTargetDays;
    }

    public Integer getWeeklyStreak() {
        return weeklyStreak;
    }

    public void setWeeklyStreak(Integer weeklyStreak) {
        this.weeklyStreak = weeklyStreak;
    }

    public Integer getLastWeekOfYear() {
        return lastWeekOfYear;
    }

    public void setLastWeekOfYear(Integer lastWeekOfYear) {
        this.lastWeekOfYear = lastWeekOfYear;
    }

    public Integer getCurrentWeekTrainedDays() {
        return currentWeekTrainedDays;
    }

    public void setCurrentWeekTrainedDays(Integer currentWeekTrainedDays) {
        this.currentWeekTrainedDays = currentWeekTrainedDays;
    }
}
