package com.hugotrevizan.modules.company.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CreateCompanyDTO(

        @Schema(example = "Company Tech")
        @NotBlank(message = "O nome não pode estar vazio")
        String name,

        @Schema(example = "companytech")
        @NotBlank(message = "O username não pode estar vazio")
        @Pattern(regexp = "\\S+", message = "O username não deve conter espaços")
        String username,

        @Schema(example = "companytech@companytech.com")
        @Email(message = "O e-mail deve ser válido")
        String email,

        @Schema(example = "company12345")
        @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
        String password,

        @Schema(example = "companytech.com")
        String website,

        @Schema(example = "Empresa de tecnologia")
        String description
) {}