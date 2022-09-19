package com.gymtrack.api.feature.user.routine.dto;

import com.gymtrack.api.feature.workout.Workout;

import javax.validation.constraints.NotBlank;

public record UserRoutineRequestDTO(Integer creatorId, @NotBlank(message = "Name is mandatory") String name,
                                    String description, Integer imageId, Workout workout) {
}
