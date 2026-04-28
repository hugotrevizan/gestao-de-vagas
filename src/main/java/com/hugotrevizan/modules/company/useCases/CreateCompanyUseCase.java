package com.hugotrevizan.modules.company.useCases;

import com.hugotrevizan.exceptions.UserFoundException;
import com.hugotrevizan.modules.company.dtos.CreateCompanyDTO;
import com.hugotrevizan.modules.company.dtos.CreateCompanyResponseDTO;
import com.hugotrevizan.modules.company.entities.CompanyEntity;
import com.hugotrevizan.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CreateCompanyResponseDTO execute(CreateCompanyDTO createCompanyDTO) {
        companyRepository.findByUsernameOrEmail(createCompanyDTO.username(), createCompanyDTO.email())
                .ifPresent((user) -> new UserFoundException());
        var passwordHash = passwordEncoder.encode(createCompanyDTO.password());
        var companyEntity = createCompanyDTO.toEntity(passwordHash);
        var companySaved = companyRepository.save(companyEntity);
        return CreateCompanyResponseDTO.fromEntity(companySaved);
    }
}
