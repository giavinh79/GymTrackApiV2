package com.gymtrack.api.feature.user_routine_exercise.dto;

import com.gymtrack.api.enums.Day;
import com.gymtrack.api.feature.set.dto.SetRequestDTO;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public record UserRoutineExerciseUpdateRequestDTO(@NotNull Integer id, @NotNull Day day, @Min(1) Integer exerciseOrder,
                                                  List<@Valid SetRequestDTO> sets) {
}
