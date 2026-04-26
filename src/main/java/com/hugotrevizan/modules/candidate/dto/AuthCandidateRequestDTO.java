package com.hugotrevizan.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record AuthCandidateRequestDTO (

    @Schema(example = "joaosilva", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "O username não pode estar vazio")
    String username,

    @Schema(example = "senha12345", requiredMode = Schema.RequiredMode.REQUIRED)
    @Length(min = 10, max = 100)
    String password
) {}
