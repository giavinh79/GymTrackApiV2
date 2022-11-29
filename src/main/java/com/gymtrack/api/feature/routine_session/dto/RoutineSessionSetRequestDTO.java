package com.gymtrack.api.feature.routine_session.dto;

import com.gymtrack.api.feature.set.dto.SetRequestDTO;

public record RoutineSessionSetRequestDTO(Integer sessionExerciseLogId, Integer exerciseId, SetRequestDTO set) {
}
