package com.hugotrevizan.modules.candidate.dtos;

import com.hugotrevizan.modules.candidate.entities.CandidateEntity;

import java.util.UUID;

public record CreateCandidateResponseDTO(
        UUID id,
        String name,
        String username,
        String email,
        String description
) {
    public static CreateCandidateResponseDTO fromEntity(CandidateEntity entity) {
        return new CreateCandidateResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getDescription()
        );
    }
}