package com.hugotrevizan.modules.company.controllers;

import com.hugotrevizan.modules.company.dtos.AuthCompanyDTO;
import com.hugotrevizan.modules.company.dtos.AuthCompanyResponseDTO;
import com.hugotrevizan.modules.company.useCases.AuthCompanyUseCase;
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

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/company")
// 1. TAG: Separa a Autenticação da Empresa num bloco exclusivo no Swagger
@Tag(name = "Autenticação - Empresa", description = "Operações de login e geração de token de acesso para empresas")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/auth")
    @Operation(summary = "Login da Empresa", description = "Autentica a empresa utilizando username e senha, retornando o Token JWT necessário para acessar rotas protegidas (como a publicação de vagas).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AuthCompanyResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Username ou senha incorretos")
    })
    public ResponseEntity<AuthCompanyResponseDTO> create(@RequestBody @Valid AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var result = this.authCompanyUseCase.execute(authCompanyDTO);
        return ResponseEntity.ok().body(result);
    }
}