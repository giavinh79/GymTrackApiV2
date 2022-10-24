package com.gymtrack.api.feature.user_routine_exercise.dto;

import com.gymtrack.api.enums.Day;
import com.gymtrack.api.feature.set.dto.SetRequestDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public record UserRoutineExerciseRequestDTO(@NotNull Day day, @NotNull Integer exerciseOrder,
                                            @NotEmpty List<@Valid SetRequestDTO> sets) {
}
