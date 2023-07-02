package com.gymtrack.api.feature.user_routine_exercise.dto;

import com.gymtrack.api.enums.Day;

import javax.validation.constraints.NotNull;

public record UserRoutineExerciseDeleteRequestDTO(@NotNull Day day) {
}
