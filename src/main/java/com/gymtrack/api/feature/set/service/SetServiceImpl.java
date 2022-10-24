package com.gymtrack.api.feature.set.service;

import com.gymtrack.api.feature.exercise_value_type.model.ExerciseValueType;
import com.gymtrack.api.feature.set.dto.SetRequestDTO;
import com.gymtrack.api.feature.set.model.Set;
import com.gymtrack.api.feature.set.repository.SetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class SetServiceImpl {
    private final SetRepository setRepository;

    public List<Set> createSets(List<SetRequestDTO> setRequestDTOs, ExerciseValueType defaultExerciseValueType) {
        List<Set> sets = setRequestDTOs
                .stream()
                .map(setRequestDTO -> Set
                        .builder()
                        .numReps(setRequestDTO.numReps())
                        .value(setRequestDTO.value())
                        .exerciseValueTypeId(Objects.requireNonNullElse(setRequestDTO.exerciseValueTypeId(), defaultExerciseValueType.getId()))
                        .exerciseValueTypeUnitId(setRequestDTO.exerciseValueTypeUnitId())
                        .build())
                .toList();

        return StreamSupport.stream(setRepository.saveAll(sets).spliterator(), false)
                .toList();
    }
}
