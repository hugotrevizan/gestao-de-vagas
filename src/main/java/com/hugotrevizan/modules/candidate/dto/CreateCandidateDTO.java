package com.hugotrevizan.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateCandidateDTO(
        @Schema(example = "João da Silva", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "O nome não pode estar vazio")
        String name,

        @Schema(example = "joaosilva", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "O username não pode estar vazio")
        String username,

        @Schema(example = "joao.silva@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
        @Email(message = "O e-mail deve ser válido")
        String email,

        @Schema(example = "senha12345", requiredMode = Schema.RequiredMode.REQUIRED)
        @Length(min = 10, max = 100)
        String password,

        @Schema(example = "Desenvolvedor em Java")
        String description
) {}