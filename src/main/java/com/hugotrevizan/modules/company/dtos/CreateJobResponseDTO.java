package com.hugotrevizan.modules.company.dtos;

import com.hugotrevizan.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

public record CreateJobResponseDTO(
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
) {
        public static CreateJobResponseDTO fromEntity(JobEntity job) {
                return new CreateJobResponseDTO(
                        job.getId(),
                        job.getDescription(),
                        job.getBenefits(),
                        job.getLevel(),
                        job.getCompanyEntity() != null ? job.getCompanyEntity().getName() : "Empresa não informada"
                );
        }
}