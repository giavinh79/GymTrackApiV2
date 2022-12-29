package com.gymtrack.api.feature.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record UserSignupDTO(@Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") String email,
                            @NotBlank String password) {
}
