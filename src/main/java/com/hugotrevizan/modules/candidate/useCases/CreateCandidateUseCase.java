package com.hugotrevizan.modules.candidate.useCases;

import com.hugotrevizan.modules.candidate.dtos.CreateCandidateDTO;
import com.hugotrevizan.modules.candidate.dtos.CreateCandidateResponseDTO;
import com.hugotrevizan.exceptions.UserFoundException;
import com.hugotrevizan.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CreateCandidateResponseDTO execute(CreateCandidateDTO createCandidateDTO) {
        candidateRepository.findByUsernameOrEmail(createCandidateDTO.username(), createCandidateDTO.email())
                .ifPresent((user) -> new UserFoundException());
        var passwordHash = passwordEncoder.encode(createCandidateDTO.password());
        var candidateEntity = createCandidateDTO.toEntity(passwordHash);
        var candidateSaved = candidateRepository.save(candidateEntity);
        return CreateCandidateResponseDTO.fromEntity(candidateSaved);
    }
}
