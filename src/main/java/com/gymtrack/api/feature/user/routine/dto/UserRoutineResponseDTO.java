package com.gymtrack.api.feature.user.routine.dto;

import com.gymtrack.api.feature.workout.Workout;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class UserRoutineResponseDTO {
    private Integer id;
    private String publicId;
    private Integer imageId;

    private String name;
    private String description;
    private Double rating;
    private OffsetDateTime createdAt;
    private Integer numTimesCopied;
    private Boolean isSelected;

    private Workout workout;
}
