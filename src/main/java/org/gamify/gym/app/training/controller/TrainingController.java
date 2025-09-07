package org.gamify.gym.app.training.controller;

import org.gamify.gym.app.training.model.Workout;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController(value = "/workout")
public class TrainingController {

    // @PostMapping(value="/workout",produces="application/json")public
    // ResponseEntity<?>createWorkout @RequestBody(CreateWorkoutDto
    // createWorkoutDto){
    // Workout workout = new Workout();
    // workout.setName(createWorkoutDto.getName());
    // workout.setDescription(createWorkoutDto.getDescription());
    // return
    // }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public String postMethodName(@RequestBody String entity) {
        // TODO: process POST request

        return entity;
    }

}
