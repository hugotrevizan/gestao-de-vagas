package com.hugotrevizan.modules.candidate.useCases;

import com.hugotrevizan.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.hugotrevizan.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(String idCandidate) {
        var candidate = this.candidateRepository.findById(UUID.fromString(idCandidate))
            .orElseThrow(() -> {
                throw new UsernameNotFoundException("User not found");
        });
        var candidateDTO = ProfileCandidateResponseDTO.builder()
        .description(candidate.getDescription())
        .username(candidate.getUsername())
        .email(candidate.getEmail())
        .name(candidate.getName())
        .id(candidate.getId())
        .build();
        return candidateDTO;
    }
}