package com.gymtrack.api.feature.set.dto;

import javax.validation.constraints.NotNull;

public record SetRequestDTO(@NotNull Integer numReps, Integer value, Integer exerciseValueTypeId,
                            Integer exerciseValueTypeUnitId) {
}
