package com.gymtrack.api.feature.user.routine.dto;

import com.gymtrack.api.feature.workout.Workout;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoutineRequestDTO {
    private Integer creatorId;

    private String name;
    private String description;

    private Integer imageId;

    private Workout workout;
}
