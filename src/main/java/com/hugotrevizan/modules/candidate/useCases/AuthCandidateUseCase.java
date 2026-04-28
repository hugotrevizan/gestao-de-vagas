package com.hugotrevizan.modules.candidate.useCases;

import com.hugotrevizan.modules.candidate.dtos.AuthCandidateRequestDTO;
import com.hugotrevizan.modules.candidate.dtos.AuthCandidateResponseDTO;
import com.hugotrevizan.modules.candidate.repositories.CandidateRepository;
import com.hugotrevizan.providers.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class AuthCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
        var candidate = candidateRepository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));

        var passwordMatches = passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException("Username/password incorrect");
        }

        var token = tokenService.generateToken(candidate.getId().toString(), List.of("CANDIDATE"));
        var expiresIn = Instant.now().plus(Duration.ofHours(2)).toEpochMilli();

        return new AuthCandidateResponseDTO(token, expiresIn);
    }
}