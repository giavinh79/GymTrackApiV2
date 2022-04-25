package com.gymtrack.api.feature.auth.dto;

import javax.validation.constraints.NotBlank;

public record UserSignupDTO(@NotBlank String email, @NotBlank String password) {
}
