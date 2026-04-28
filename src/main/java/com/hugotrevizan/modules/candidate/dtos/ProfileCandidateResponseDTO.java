package com.hugotrevizan.modules.candidate.dtos;

import com.hugotrevizan.modules.candidate.entities.CandidateEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record ProfileCandidateResponseDTO(

        @Schema(example = "Desenvolvedor em Java")
        String description,

        @Schema(example = "joaosilva")
        String username,

        @Schema(example = "joao.silva@gmail.com")
        String email,

        UUID id,

        @Schema(example = "João da Silva")
        String name

) {
    public static ProfileCandidateResponseDTO fromEntity(CandidateEntity entity) {
        return new ProfileCandidateResponseDTO(
                entity.getDescription(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getId(),
                entity.getName()
        );
    }
}