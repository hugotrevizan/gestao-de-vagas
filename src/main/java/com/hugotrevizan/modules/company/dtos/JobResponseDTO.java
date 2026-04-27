package com.hugotrevizan.modules.company.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

public record JobResponseDTO(
        @Schema(example = "823467c3-380d-4f0e-b85e-63f7356c3674")
        UUID id,

        @Schema(example = "Desenvolvedor Java Júnior")
        String description,

        @Schema(example = "Plano de Saúde, VR, Home Office")
        String benefits,

        @Schema(example = "JUNIOR")
        String level,

        @Schema(example = "Tecnologia")
        String companyName
) {}