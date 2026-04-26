package com.hugotrevizan.modules.candidate.dto;

import java.util.UUID;

public record CreateCandidateResponseDTO(
        UUID id,
        String name,
        String username,
        String email,
        String description
) {}