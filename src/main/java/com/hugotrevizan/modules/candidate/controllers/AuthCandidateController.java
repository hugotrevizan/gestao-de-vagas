package com.hugotrevizan.modules.candidate.controllers;

import com.hugotrevizan.modules.candidate.dtos.AuthCandidateRequestDTO;
import com.hugotrevizan.modules.candidate.dtos.AuthCandidateResponseDTO;
import com.hugotrevizan.modules.candidate.useCases.AuthCandidateUseCase;
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
@RequestMapping("/candidate")
@Tag(name = "Autenticação - Candidato", description = "Operações de login e geração de token de acesso")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/auth")
    @Operation(summary = "Login do Candidato", description = "Autentica o candidato utilizando username e senha, retornando o Token JWT necessário para acessar as rotas protegidas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AuthCandidateResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Usuário ou senha incorretos")
    })
    public ResponseEntity<AuthCandidateResponseDTO> auth(@Valid @RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
        var token = authCandidateUseCase.execute(authCandidateRequestDTO);
        return ResponseEntity.ok().body(token);
    }
}