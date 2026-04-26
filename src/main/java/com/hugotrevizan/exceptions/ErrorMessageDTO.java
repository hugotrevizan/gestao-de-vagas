package com.hugotrevizan.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {

    @Schema(example = "O campo (email) deve conter um email válido")
    private String message;

    @Schema(example = "email")
    private String field;
}