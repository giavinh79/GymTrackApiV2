package com.gymtrack.api.feature.image.dto;

import java.io.Serial;
import java.io.Serializable;

public record ImageResponseDTO(String url, String fileId) implements Serializable {
    @Serial
    private static final long serialVersionUID = -4658829668143500827L;
}
