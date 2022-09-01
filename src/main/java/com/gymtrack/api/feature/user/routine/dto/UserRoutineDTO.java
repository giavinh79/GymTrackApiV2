package com.gymtrack.api.feature.user.routine.dto;

import com.gymtrack.api.feature.workout.Workout;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserRoutineDTO {
    private Integer id;
    private String publicId;
    private Integer imageId;

    private String name;
    private String description;
    private Double rating;
    private LocalDateTime createdAt;
    private Integer numTimesCopied;
    private Boolean isSelected;

    private Workout workout;

    /*
    workouts: {
        monday: Exercise
        tuesday: Exercise model
        wednesday: Exercise model
        thursday:
        friday:
        saturday:
        sunday:
    }
     */
}
