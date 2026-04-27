package com.hugotrevizan.modules.company.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateJobDTO {

    @Schema(example = "Desenvolvedor(a) Back-end Java Júnior", requiredMode = Schema.RequiredMode.REQUIRED, description = "Título e descrição detalhada da vaga")
    @NotBlank(message = "Esse campo é obrigatório")
    private String description;

    @Schema(example = "Vale Refeição R$ 40/dia, Plano de Saúde completo, Gympass", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Esse campo é obrigatório")
    private String benefits;

    @Schema(example = "JUNIOR", requiredMode = Schema.RequiredMode.REQUIRED, description = "Nível de experiência (ex: JUNIOR, PLENO, SENIOR)")
    @NotBlank(message = "Esse campo é obrigatório")
    private String level;
}