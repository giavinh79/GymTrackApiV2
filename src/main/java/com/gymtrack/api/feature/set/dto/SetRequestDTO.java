package com.gymtrack.api.feature.set.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record SetRequestDTO(@NotNull @Min(1) @Max(10000) Integer numReps, Integer value, Integer exerciseValueTypeId,
                            Integer exerciseValueTypeUnitId) {
}
