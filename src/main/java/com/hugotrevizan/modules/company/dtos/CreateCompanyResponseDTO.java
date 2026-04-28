package com.hugotrevizan.modules.company.dtos;

import com.hugotrevizan.modules.company.entities.CompanyEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record CreateCompanyResponseDTO(

        @Schema(example = "123e4567-e89b-12d3-a456-426614174000")
        UUID id,

        @Schema(example = "Company Tech")
        String name,

        @Schema(example = "companytech")
        String username,

        @Schema(example = "companytech@companytech.com")
        String email,

        @Schema(example = "companytech.com")
        String website,

        @Schema(example = "Empresa de tecnologia")
        String description

) {
    public static CreateCompanyResponseDTO fromEntity(CompanyEntity entity) {
        return new CreateCompanyResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getWebsite(),
                entity.getDescription()
        );
    }
}