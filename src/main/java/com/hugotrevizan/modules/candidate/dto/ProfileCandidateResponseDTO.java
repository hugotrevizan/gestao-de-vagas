package com.hugotrevizan.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    @Schema(example = "Desenvolvedor em Java")
    private String description;

    @Schema(example = "joaosilva")
    private String username;

    @Schema(example = "joao.silva@gmail.com")
    private String email;
    private UUID id;

    @Schema(example = "João da Silva")
    private String name;
}
