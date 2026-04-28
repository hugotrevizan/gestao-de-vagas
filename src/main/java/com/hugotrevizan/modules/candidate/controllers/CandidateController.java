package com.hugotrevizan.modules.candidate.controllers;

import com.hugotrevizan.modules.candidate.dtos.CreateCandidateDTO;
import com.hugotrevizan.modules.candidate.dtos.CreateCandidateResponseDTO;
import com.hugotrevizan.modules.candidate.dtos.ProfileCandidateResponseDTO;
import com.hugotrevizan.modules.candidate.useCases.CreateCandidateUseCase;
import com.hugotrevizan.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import com.hugotrevizan.modules.candidate.useCases.ProfileCandidateUseCase;
import com.hugotrevizan.modules.company.dtos.CreateJobResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Operações e informações do candidato")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @PostMapping("/")
    @Operation(summary = "Cadastro de candidato", description = "Essa função é responsável por cadastrar um novo candidato no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CreateCandidateResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos campos ou usuário já existente")
    })
    public ResponseEntity<CreateCandidateResponseDTO> create(@Valid @RequestBody CreateCandidateDTO createCandidateDTO) {
        return ResponseEntity.ok().body(createCandidateUseCase.execute(createCandidateDTO));
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Perfil do candidato", description = "Essa função é responsável por buscar as informações do perfil do candidato logado")
    @SecurityRequirement(name = "jwt_auth")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado no banco de dados"),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido")
    })
    public ResponseEntity<ProfileCandidateResponseDTO> get(HttpServletRequest request) {
        return ResponseEntity.ok().body(
                profileCandidateUseCase.execute(request.getAttribute("candidate_id").toString())
        );
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Listagem de vagas disponíveis", description = "Essa função é responsável por listar todas as vagas disponíveis, baseada no filtro de texto")
    @SecurityRequirement(name = "jwt_auth")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = CreateJobResponseDTO.class)))
            }),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido")
    })
    public ResponseEntity<List<CreateJobResponseDTO>> findJobByFilter(@RequestParam String filter) {
        return ResponseEntity.ok().body(listAllJobsByFilterUseCase.execute(filter));
    }
}