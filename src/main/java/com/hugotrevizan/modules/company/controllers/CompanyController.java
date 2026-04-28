package com.hugotrevizan.modules.company.controllers;

import com.hugotrevizan.modules.company.dtos.CreateCompanyDTO;
import com.hugotrevizan.modules.company.dtos.CreateCompanyResponseDTO;
import com.hugotrevizan.modules.company.useCases.CreateCompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@Tag(name = "Empresa", description = "Operações destinadas às empresas (Cadastro e Gestão)")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("/")
    @Operation(summary = "Cadastro de empresa", description = "Essa função é responsável por cadastrar uma nova empresa no sistema para que ela possa publicar vagas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CreateCompanyResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Empresa ou e-mail já cadastrados")
    })
    public ResponseEntity<CreateCompanyResponseDTO> create(@Valid @RequestBody CreateCompanyDTO createCompanyDTO) {
        return ResponseEntity.ok().body(createCompanyUseCase.execute(createCompanyDTO));
    }
}