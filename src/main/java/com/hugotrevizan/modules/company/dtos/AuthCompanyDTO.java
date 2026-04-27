package com.hugotrevizan.modules.company.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthCompanyDTO(
        @Schema(example = "companytech", requiredMode = Schema.RequiredMode.REQUIRED)
        String username,

        @Schema(example = "company12345", requiredMode = Schema.RequiredMode.REQUIRED)
        String password
) {}