package org.gamify.gym.app.training.dto;

import java.sql.Date;
import java.sql.Time;

public class ExerciseLogListResponseDto {
    private Double weight;

    private int reps;

    private Time time_in;

    private Date day_made;

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
}
