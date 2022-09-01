package com.gymtrack.api.feature.workout;

import com.gymtrack.api.feature.exercise.model.Exercise;
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
    private List<Exercise> monday;
    private List<Exercise> tuesday;
    private List<Exercise> wednesday;
    private List<Exercise> thursday;
    private List<Exercise> friday;
    private List<Exercise> saturday;
    private List<Exercise> sunday;
}
