package com.hugotrevizan.modules.candidate.useCases;

import com.hugotrevizan.modules.candidate.dtos.ProfileCandidateResponseDTO;
import com.hugotrevizan.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(String idCandidate) {
        var candidate = candidateRepository.findById(UUID.fromString(idCandidate))
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return ProfileCandidateResponseDTO.fromEntity(candidate);
    }
}