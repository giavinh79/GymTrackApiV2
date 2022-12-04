package com.gymtrack.api.feature.workout;

import com.gymtrack.api.feature.routine_exercise.dto.RoutineExerciseResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Workout {
    private List<RoutineExerciseResponseDTO> monday;
    private List<RoutineExerciseResponseDTO> tuesday;
    private List<RoutineExerciseResponseDTO> wednesday;
    private List<RoutineExerciseResponseDTO> thursday;
    private List<RoutineExerciseResponseDTO> friday;
    private List<RoutineExerciseResponseDTO> saturday;
    private List<RoutineExerciseResponseDTO> sunday;
}
