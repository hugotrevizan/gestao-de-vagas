package com.hugotrevizan.modules.company.useCases;

import com.hugotrevizan.modules.company.dtos.AuthCompanyDTO;
import com.hugotrevizan.modules.company.dtos.AuthCompanyResponseDTO;
import com.hugotrevizan.modules.company.repositories.CompanyRepository;
import com.hugotrevizan.providers.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Instant;
import java.time.Duration;
import java.util.List;

@Service
public class AuthCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = companyRepository.findByUsername(authCompanyDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("User/password incorrect"));

        var passwordMatches = passwordEncoder.matches(authCompanyDTO.password(), company.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException("User/password incorrect");
        }
        var token = tokenService.generateToken(company.getId().toString(), List.of("COMPANY"));
        var expiresIn = Instant.now().plus(Duration.ofHours(2)).toEpochMilli();
        return new AuthCompanyResponseDTO(token, expiresIn);
    }
}